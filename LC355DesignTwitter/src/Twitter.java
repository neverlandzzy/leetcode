import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;


public class Twitter {

	/*
	 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most 
	 * recent tweets in the user's news feed. Your design should support the following methods:
	 * 
	 * postTweet(userId, tweetId): Compose a new tweet.
	 * getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by 
	 * users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
	 * follow(followerId, followeeId): Follower follows a followee.
	 * unfollow(followerId, followeeId): Follower unfollows a followee.
	 *
	 * Example:
	 * Twitter twitter = new Twitter();
	 * // User 1 posts a new tweet (id = 5).
	 * twitter.postTweet(1, 5);
	 * 
	 * // User 1's news feed should return a list with 1 tweet id -> [5].
	 * twitter.getNewsFeed(1);
	 * 
	 * // User 1 follows user 2.
	 * twitter.follow(1, 2);
	 * 
	 * // User 2 posts a new tweet (id = 6).
	 * twitter.postTweet(2, 6);
	 * 
	 * // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
	 * // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
	 * twitter.getNewsFeed(1);
	 * 
	 * // User 1 unfollows user 2.
	 * twitter.unfollow(1, 2);
	 * 
	 * // User 1's news feed should return a list with 1 tweet id -> [5],
	 * // since user 1 is no longer following user 2.
	 * twitter.getNewsFeed(1);
	 */
	
	int count = 0;
	
	class Tweet {
		int id;
		int timeStamp;
		
		public Tweet(int id) {
			this.id = id;
			this.timeStamp = count;
		}
	}
	
    /** Initialize your data structure here. */
	Map<Integer, List<Tweet>> tweetMap;
	Map<Integer, Set<Integer>> followMap;
	final int SIZE = 10;
	
    public Twitter() {
    	tweetMap = new HashMap<>();
    	followMap = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
    	count++;
    	Tweet tweet = new Tweet(tweetId);
    	
        if (!tweetMap.containsKey(userId)) {
        	tweetMap.put(userId, new ArrayList<>());
        }
        
        tweetMap.get(userId).add(tweet);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed 
     * or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> heap = new PriorityQueue<>(new Comparator<Tweet>() {
        	public int compare(Tweet t1, Tweet t2) {
        		return t2.timeStamp - t1.timeStamp;
        	}
        });
        
        List<Integer> result = new ArrayList<>();
        
        if (tweetMap.containsKey(userId)) {
        	List<Tweet> tweetList = tweetMap.get(userId);
        	for (int i = tweetList.size() - 1; i >= 0 && i >= tweetList.size() - 10; i--) {
        		heap.offer(tweetList.get(i));
        	}
        }
        
        if (followMap.containsKey(userId)) {
        	for (int followee: followMap.get(userId)) {
        		if (userId != followee && tweetMap.containsKey(followee)) {
        			List<Tweet> tweetList = tweetMap.get(followee);
        			for (int i = tweetList.size() - 1; i >= 0 && i >= tweetList.size() - 10; i--) {
                		heap.offer(tweetList.get(i));
                	}
        		}
        	}
        }
        
        int counter = 0;
        while (!heap.isEmpty() && counter < SIZE) {
        	result.add(heap.poll().id);
        	counter++;
        }
        
        return result;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
    	if (!followMap.containsKey(followerId)) {
    		followMap.put(followerId, new HashSet<>());
    	}
    	
    	followMap.get(followerId).add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
    	if (!followMap.containsKey(followerId)) {
    		return;
    	}
    	followMap.get(followerId).remove(followeeId);
    }
}
