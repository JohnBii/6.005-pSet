/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.ArrayList;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even
 * exist as a key in the map; this is true even if A is followed by other people
 * in the network. Twitter usernames are not case sensitive, so "ernie" is the
 * same as "ERNie". A username should appear at most once as a key in the map or
 * in any given map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets a list of tweets providing the evidence, not modified by this
     *               method.
     * @return a social network (as defined above) in which Ernie follows Bert if
     *         and only if there is evidence for it in the given list of tweets. One
     *         kind of evidence that Ernie follows Bert is if Ernie
     * @-mentions Bert in a tweet. This must be implemented. Other kinds of evidence
     *            may be used at the implementor's discretion. All the Twitter
     *            usernames in the returned social network must be either authors
     *            or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
        final Map<String, Set<String>> network = new HashMap<>();

        for (Tweet t : tweets) {
            final String author = t.getAuthor();
            if (!Extract.alreadyMentioned(network.keySet(), author)) {
                network.put(author, new HashSet<>());
            }

            final Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(t));
            for (String user : mentionedUsers) {
                // users cannot follow themselves
                if (author.toLowerCase().equals(user.toLowerCase())) {
                    continue;
                }

                final Set<String> followings = network.get(author);
                if (!Extract.alreadyMentioned(followings, user)) {
                    followings.add(user);
                }
            }
        }
        return Collections.unmodifiableMap(network);
    }

    /**
     * Find the people in a social network who have the greatest influence, in the
     * sense that they have the most followers.
     * 
     * @param followsGraph a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers1(Map<String, Set<String>> followsGraph) {
        final SortedSet<UserFollowerCount> followerRecords = new TreeSet<>();
        
        for (String user : followsGraph.keySet()) {
            // add the user to the followerRecords 
            boolean containedUser = false;
            for (UserFollowerCount record : followerRecords) {
                if (record.sameUser(user)) {
                    containedUser = true;
                    break;
                }
            }
            if (!containedUser) {
                followerRecords.add(new UserFollowerCount(user, 0));
            }
            
            // examine and add all of the followings of the user to the followerRecords
            for (String following : followsGraph.get(user)) {
                boolean containedFollowing = false;
                for (UserFollowerCount record : new HashSet<>(followerRecords)) {
                    if (record.sameUser(following)) {
                        followerRecords.remove(record);
                        record.followerIncrement();
                        followerRecords.add(record);
                        containedFollowing = true;
                        break;
                    }
                }
                if (!containedFollowing) {
                    followerRecords.add(new UserFollowerCount(following, 1));
                }
            }
            
        }
        
        final List<String> result = new ArrayList<>();
        for (UserFollowerCount record : followerRecords) {
            result.add(record.getUserName());
        }
        return Collections.unmodifiableList(result);
    }
    
    /**
     * Find the people in a social network who have the greatest influence, in the
     * sense that they have the most followers.
     * 
     * @param followsGraph a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
        // produce by lsk & chatGPT 3.5
        
        final Map<String, Integer> influencerSum = new HashMap<>();
        
        for (String user : followsGraph.keySet()) {
            influencerSum.putIfAbsent(user, 0);
            for (String follow : followsGraph.get(user)) {
                influencerSum.put(follow, influencerSum.getOrDefault(follow, 0) + 1);
            }
        }
        
        final List<String> result = influencerSum.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        
        return Collections.unmodifiableList(result);
    }
    
}

