/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here. See the
     * ic03-testing exercise for examples of what a testing strategy comment looks
     * like. Make sure you have partitions.
     * 
     * 测试策略：guessFollowsGraph 1. tweet list =0，!=0 2. author 在1条twitter中 @了
     * 0个人，1个人，2个人 3. author 在1条、2条twitter中@了人 4. author @过同一个人1次、超过1次
     * 5. @同一个人，用不同的大小写 6. @了自己，不能自己follow自己
     * 
     * 测试策略：influencers 1. followGraph =0, !=0 2. users 分别有 0，1，2个followers
     */

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    /*
     * 测试 tweet list =0
     */
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());

        assertTrue("expected empty graph", followsGraph.isEmpty());
    }

    /*
     * 测试 tweet list !=0 测试 author 在1条twitter 中 @了0个人，1个人，2个人 测试 author 在1条twitter
     * 中 @ 了人 测试 author @过同一个人1次
     */
    @Test
    public void testGuessFollowsGraphOneTweetMultiMention() {
        final Instant d1 = Instant.parse("2015-02-17T09:00:00Z");
        final Tweet tweet1 = new Tweet(1, "John", "let's say hello to the world", d1);
        final Tweet tweet2 = new Tweet(2, "V", "miss you @Panan very much", d1);
        final Tweet tweet3 = new Tweet(3, "Jonny", "my best brother: @John and @V", d1);

        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2, tweet3));
        assertFalse("expected non empty graph", followsGraph.isEmpty());
        assertTrue("expected John following nobody",
                !followsGraph.containsKey("John") || followsGraph.get("John").isEmpty());
        assertTrue("expected V following Panan", followsGraph.get("V").containsAll(Arrays.asList("Panan")));
        assertTrue("expected Jonny following 2 guys",
                followsGraph.get("Jonny").containsAll(Arrays.asList("John", "V")));
    }

    /*
     * 测试 author 在2条twitter中@了人
     */
    @Test
    public void testGuessFollowsGraphMentionUsersInTwoTweets() {
        final Instant d1 = Instant.parse("2015-02-17T09:00:00Z");
        final Tweet tweet1 = new Tweet(1, "V", "@Johny, let's say hello to the world", d1);
        final Tweet tweet2 = new Tweet(2, "V", "miss you @Panan very much", d1);

        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2));
        assertFalse("expected non empty graph", followsGraph.isEmpty());
        assertTrue("expected V following 2 guys", followsGraph.get("V").containsAll(Arrays.asList("Johny", "Panan")));
    }

    /*
     * 测试 author @过同一个人超过1次
     */
    @Test
    public void testGuessFollowsGraphMentionTwice() {
        final Instant d1 = Instant.parse("2015-02-17T09:00:00Z");
        final Tweet tweet1 = new Tweet(1, "V", "@Johny, let's say hello to the world", d1);
        final Tweet tweet2 = new Tweet(2, "V", "miss you @Jonny very much", d1);

        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2));
        assertTrue("expected V following 1 guy", followsGraph.get("V").containsAll(Arrays.asList("Johny")));
    }

    /*
     * 测试 author @同一个人，用不同的大小写 测试 @自己，不应该自己follow自己
     */
    @Test
    public void testGuessFollowsGraphMentionInDifferentCase() {
        final Instant d1 = Instant.parse("2015-02-17T09:00:00Z");
        final Tweet tweet1 = new Tweet(1, "V", "@Johny, let's say hello to the world and @V!", d1);
        final Tweet tweet2 = new Tweet(2, "V", "miss you @jonny very much", d1);

        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2));
        assertFalse("expected non empty graph", followsGraph.isEmpty());
        assertTrue("expected V following 1 guy", followsGraph.get("V").containsAll(Arrays.asList("Johny"))
                || followsGraph.get("V").containsAll(Arrays.asList("johny")));
    }

    /*
     * 测试 followGraph = 0
     */
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);

        assertTrue("expected empty list", influencers.isEmpty());
    }

    /*
     * 测试 followGraph = !0 测试 users 分别有 0，1，2个followers
     */
    @Test
    public void testInfluencersInSequence() {
        final Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("V", new HashSet<>());
        followsGraph.put("Jonny", new HashSet<>(Arrays.asList("V")));
        followsGraph.put("Judy", new HashSet<>(Arrays.asList("V", "Thomsons")));
        List<String> influencers = SocialNetwork.influencers(followsGraph);

        assertFalse("expected non-empty list", influencers.isEmpty());
        assertTrue("expected four users in influencers",
                influencers.containsAll(Arrays.asList("V", "Thomsons", "Judy", "Jonny")));
        assertEquals("expected 1st user who have 2 followers", "V", influencers.get(0));
        assertEquals("expected 2nd user who have 1 follower", "Thomsons", influencers.get(1));
        assertTrue("expected 3rd user who have 0 follower",
                influencers.get(2).equals("Judy") || influencers.get(2).equals("Jonny"));
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version. DO NOT
     * strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own that
     * you have put in SocialNetwork, because that means you're testing a stronger
     * spec than SocialNetwork says. If you need such helper methods, define them in
     * a different class. If you only need them in this test class, then keep them
     * in this test class.
     */

}
