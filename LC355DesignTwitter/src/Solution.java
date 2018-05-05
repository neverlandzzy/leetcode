
public class Solution {
	public static void main(String[] args) {
		Twitter twitter = new Twitter();
		
		// Test case 1:
		/*
		twitter.postTweet(1, 5);
		System.out.println(twitter.getNewsFeed(1));
		
		twitter.follow(1, 2);
		twitter.postTweet(2, 6);
		
		System.out.println(twitter.getNewsFeed(1));
		
		twitter.unfollow(1, 2);
		System.out.println(twitter.getNewsFeed(1));
		*/
		
		// Test case 2:
		/*
		twitter.postTweet(1, 5);
		twitter.follow(1, 1);
		System.out.println(twitter.getNewsFeed(1));
		*/
		
		// Test case 3:
		twitter.postTweet(1, 5);
		twitter.postTweet(1, 3);
		twitter.postTweet(1, 101);
		twitter.postTweet(1, 13);
		twitter.postTweet(1, 10);
		twitter.postTweet(1, 2);
		twitter.postTweet(1, 94);
		twitter.postTweet(1, 505);
		twitter.postTweet(1, 333);
		twitter.postTweet(1, 22);
		twitter.postTweet(1, 11);
		System.out.println(twitter.getNewsFeed(1));
	}
}
