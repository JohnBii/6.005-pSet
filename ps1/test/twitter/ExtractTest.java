/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     * 
     * 测试对象1：getTimespan
     * 测试策略：
     * 1. tweets count =  1, 2，>2
     */

    
    // 用于测试getTimespan的参数
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2024-04-01T18:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "John", "I am from the future", d3);
    

    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    /*
     * test of getTimespan
     * 测试 tweets count =1
     */
    @Test
    public void testGetTimespanOneTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet3));
        assertEquals("expected start", d3, timespan.getStart());
        assertEquals("expected end", d3, timespan.getEnd());
    }
    
    /*
     * test of getTimespan
     * 测试 tweets count =2
     */
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }
    
    /*
     * test of getTimespan
     * 测试 tweets count >2
     */
    @Test
    public void testGetTimespanThreeTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2, tweet3));
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d3, timespan.getEnd());
    }
    
    
    /*
    * 测试对象2：getMentionedUsers
    * 测试策略：
    * 1. tweets count = 0, 1, >1
    * 2. tweets 中包含 0，1，>1 的用户名
    * 3. tweets 中被提及的用户名前后不能包含合法用户名的字符
    * 4. tweets 中用户名包含非法字符
    * 5. tweets 中相同用户名出现两次，一次大写，一次小写
    * 6. mentioned user name include a-zA-Z0-9-_
    * 7. mentioned user name seperated by ,.
    */
    // 用于测试getMentionedUsers的参数
    private static final Tweet tweetIncludeEmailAddress = new Tweet(6, "john", "my email address is mit@edu***", d1);
    private static final Tweet tweetInvalidMentionedUser = new Tweet(7, "john", "I want to thank #ADF@%^Kfhsda.", d1);
    private static final Tweet tweetMentionedUserOnce = new Tweet(8, "john", "@vale is my best friend", d1);
    private static final Tweet tweetMentionedUserTwice = new Tweet(9, "john", "@Vale. is my best friend", d1);
    private static final Tweet tweetMentionedUserAnother = new Tweet(10, "john", "@Jimm279-_y, is my best friend too", d1);
    
    
    /*
     * test of getMentionedUsers
     * 测试 tweet count =0
     */
    @Test
    public void testGetMentionedUsersNoTweet() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList());
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    /*
     * test of getMentionedUsers
     * 测试 mentioned user =0
     * 测试 tweet count = 1
     */
    @Test
    public void testGetMentionedUsersNoUser() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }

    /*
     * 测试 tweet count >1
     * 测试 提及两次同一个user，但不同大小写
     * 测试 tweets 包含用户 =1
     */
    @Test
    public void testGetMentionedUsersDifferentCase() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweetMentionedUserOnce, tweetMentionedUserTwice));
        assertTrue("expected set size == 1", mentionedUsers.size() == 1);
        assertTrue("expected set[0] == vale or Vale", mentionedUsers.contains("vale") || mentionedUsers.contains("Vale"));
    }
    
    /*
     * 测试 tweet count >1
     * 测试 tweets 包含用户 >1
     * 测试 提及的用户名被,.分隔
     * 测试 提及的用户名包含a-zA-Z0-9-_
     */
    @Test
    public void testGetMentionedUsersMultiUsers() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweetMentionedUserOnce, tweetMentionedUserAnother));
        assertTrue("expected set size == 2", mentionedUsers.size() == 2);
        assertTrue("expected mentionedUsers contain vale, Jimm279-_y", mentionedUsers.contains("vale") && mentionedUsers.contains("Jimm279-_y"));
    }
    
    /*
     * test of getMentionedUsers
     * 测试 不合法的用户名
     */
    @Test
    public void testGetMentionedUsersInvalidUserName() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweetInvalidMentionedUser));
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    /*
     * test of getMentionedUsers
     * 测试 中被提及的用户名前后不能包含合法用户名的字符
     */
    @Test
    public void testGetMentionedUsersInvalidSurround() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweetIncludeEmailAddress));
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}
