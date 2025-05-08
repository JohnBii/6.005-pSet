/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FilterTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     * 
     * 测试策略：writtenBy
     * 1. 测试 tweet list： =0 !=0
     * 2. 测试 tweet list：tweet总数为3时，包含该用户的tweet的数量：0， 1， 2
     * 3. 测试 tweet 的数量大于 1 时，顺序与原本list的顺序一致
     * 
     * 测试策略：timeSpan
     * 1. 测试 tweet list: =0, !=0
     * 2. 测试 tweet 的时间 小于timeSpan，等于timeSpan起点，位于timeSpan里，等于timeSpan终点, 大于timeSpan
     * 3. 测试 tweet 的数量大于 1 时，顺序与原本list的顺序一致
     * 
     * 测试策略：containing
     * 1. 测试 tweet list：=0 !=0
     * 2. 测试 words list：=0 !=0
     * 3. 测试 word 与 tweet 中的大小写不同时，是否依然能contain
     * 4. 测试最终的 tweet list 中 tweet 的顺序是否与原 tweet list 相同
     * 5. tweet中，有0条tweet contain words
     * 6. 一个word能匹配多条tweets；多个word能分别匹配tweets
     */
    
    private static final Instant d1 = Instant.parse("2015-02-17T09:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T09:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T12:00:00Z");
    private static final Instant d5 = Instant.parse("2017-02-17T12:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "bbitdiddle", "hello, world", d3);
    private static final Tweet tweet4 = new Tweet(4, "john", "talk in 30 minutes #hype", d4);
    private static final Tweet tweet5 = new Tweet(5, "fhilly", "rivest talk in 3332 minutes #hype", d5);
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    /*
     * 测试 tweet list = 0
     */
    @Test
    public void testWrittenByNoTweets() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(), "bbitdiddle");
        
        assertTrue("expected empty list", writtenBy.isEmpty());
    }
    
    /*
     * 测试 tweet 的总数为3时，包含该用户的数量为2
     * 测试 tweet list != 0
     * 测试 tweets in writtenBy 的顺序与原本list的顺序一致
     */
    @Test
    public void testWrittenByMultipleTweetsTwoResult() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2, tweet3), "bbitdiddle");
        
        assertFalse("expected non-empty list", writtenBy.isEmpty());
        assertTrue("expected list to catain tweets", writtenBy.containsAll(Arrays.asList(tweet2, tweet3)));
        assertEquals("expected first element of list is tweet2", 0, writtenBy.indexOf(tweet2));
        assertEquals("expected second element of list is tweet3", 1, writtenBy.indexOf(tweet3));
        
    }
    
    /*
     * 测试 tweet 的总数为3时，包含该用户的数量为1
     * 测试 tweet list != 0
     */
    @Test
    public void testWrittenByMultipleTweetsSingleResult() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet3, tweet4), "bbitdiddle");
        
        assertEquals("expected singleton list", 1, writtenBy.size());
        assertTrue("expected list to contain tweet", writtenBy.contains(tweet3));
    }
    
    /*
     * 测试 tweet 的总数为3时，包含该用户的数量为0
     * 测试 tweet list != 0
     */
    @Test
    public void testWrittenByMultipleTweetsNoResult() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet4, tweet5), "bbitdiddle");
        
        assertTrue("expected empty list", writtenBy.isEmpty());
    }
 
    /*
     * 测试 tweet list = 0
     */
    @Test
    public void testInTimespanNoTweets() {
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(), new Timespan(d1, d2));
        
        assertTrue("expected empty list", inTimespan.isEmpty());
    }
    
    /*
     * 测试 tweet 的时间 小于timeSpan，等于timeSpan起点，位于timeSpan里，等于timeSpan终点, 大于timeSpan
     * 测试 tweet 的数量大于 1 时，顺序与原本list的顺序一致
     */
    @Test
    public void testInTimespanMultipleTweetsMultipleResults() {
        Instant testStart = Instant.parse("2016-02-17T09:00:00Z");
        Instant testEnd = Instant.parse("2016-02-17T12:00:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5), new Timespan(testStart, testEnd));
        
        assertFalse("expected non-empty list", inTimespan.isEmpty());
        assertTrue("expected list to contain tweets", inTimespan.containsAll(Arrays.asList(tweet2, tweet3, tweet4)));
        assertEquals("expected same order", 0, inTimespan.indexOf(tweet2));
        assertEquals("expected same order", 1, inTimespan.indexOf(tweet3));
        assertEquals("expected same order", 2, inTimespan.indexOf(tweet4));
    }
    
    /*
     * 测试 tweet list = 0
     * 测试 word != 0
     */
    @Test
    public void testContainingNoTweet() {
        List<Tweet> containing = Filter.containing(Arrays.asList(), Arrays.asList("talk"));
        
        assertTrue("expected empty list", containing.isEmpty());
    }
    
    /*
     *  测试 words = 0
     *  测试 tweet list !=0
     */
    @Test
    public void testContainingNoWord() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList());
        
        assertTrue("expected empty list", containing.isEmpty());
    }
    
    /*
     * 测试tweet中的词与word中大小写不同时，是否能contain
     * 测试最终的 tweet list 中 tweet 的顺序是否与原 tweet list 相同
     * 一个word能匹配多条tweets
     */
    @Test
    public void testContainingInOriginOrder() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet2, tweet3, tweet4, tweet5), Arrays.asList("IN"));
        
        assertFalse("expected non-empty list", containing.isEmpty());
        assertTrue("expected list to contain tweets", containing.containsAll(Arrays.asList(tweet2, tweet4, tweet5)));
        assertEquals("expected same order", 0, containing.indexOf(tweet2));
        assertEquals("expected same order", 1, containing.indexOf(tweet4));
        assertEquals("expected same order", 2, containing.indexOf(tweet5));
    }

    /*
     * 测试 tweet list 中没有tweets contain any words
     */
    @Test
    public void testContainingNoContain() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2, tweet3), Arrays.asList("panda", "tiger"));
        
        assertTrue("expected empty list", containing.isEmpty());
    }
    
    
    /*
     * 多个word能分别匹配tweets
     * 测试最终的 tweet list 中 tweet 的顺序是否与原 tweet list 相同
     */
    @Test
    public void testContainingMultiWords() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet3, tweet1, tweet2), Arrays.asList("minutes", "world"));
        
        assertFalse("expected non-empty list", containing.isEmpty());
        assertTrue("expected list to contain tweets", containing.containsAll(Arrays.asList(tweet3, tweet2)));
        assertEquals("expected same order", 0, containing.indexOf(tweet3));
        assertEquals("expected same order", 1, containing.indexOf(tweet2));
    }

    /*
     * Warning: all the tests you write here must be runnable against any Filter
     * class that follows the spec. It will be run against several staff
     * implementations of Filter, which will be done by overwriting
     * (temporarily) your version of Filter with the staff's version.
     * DO NOT strengthen the spec of Filter or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Filter, because that means you're testing a stronger
     * spec than Filter says. If you need such helper methods, define them in a
     * different class. If you only need them in this test class, then keep them
     * in this test class.
     */

}
