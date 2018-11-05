package CompanyOriented.Amazon;

import java.util.*;

/**
 * users can post tweets
 * if follow, able to see 10 most recent tweets
 * if unfollow, unable
 * <p>
 * postTweet(uid, tid)
 * getNewsFeed(uid): get 10 most recent(ordered, by users uid followed or by uid herself)
 * follow(ferid, feeid) fer follow fee
 * unfollow(ferid, feeid) fer unfollow fee
 */
/*
Map<uid, LinkedList>
follower followee post
Map<uid, List<uid>> followeeToFollowers
 */
public class Twitter {
    class Post {
        int idPost;
        int timePost;

        public Post(int idPost, int timePost) {
            this.idPost = idPost;
            this.timePost = timePost;
        }
    }

    Map<Integer, LinkedList<Integer>> followerToFollowees;
    Map<Integer, LinkedList<Post>> userToPosts;
    PriorityQueue<Post> sortedPosts;
    int postTime;

    public Twitter() {
        followerToFollowees = new HashMap<>();
        userToPosts = new HashMap<>();
        sortedPosts = new PriorityQueue<>(new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                return o2.timePost - o1.timePost;
            }
        });
        postTime = 0;
    }

    public void postTweet(int userId, int tweetId) {
        if (!userToPosts.containsKey(userId)) {
            userToPosts.put(userId, new LinkedList<>());
        }
        userToPosts.get(userId).add(new Post(tweetId, postTime++));
    }

    public List<Integer> getNewsFeed(int userId) {
        sortedPosts.clear();
        if (userToPosts.containsKey(userId)) {
            for (Post post : userToPosts.get(userId)) {
                sortedPosts.add(post);
            }
        }
        if (followerToFollowees.containsKey(userId)) {
            for (int uid : followerToFollowees.get(userId)) {
                if (userToPosts.containsKey(uid)) {
                    for (Post post : userToPosts.get(uid)) {
                        sortedPosts.add(post);
                    }
                }
            }
        }
        List<Integer> displayedPosts = new LinkedList<>();
        while (displayedPosts.size() < 10 && !sortedPosts.isEmpty()) {
            displayedPosts.add(sortedPosts.poll().idPost);
        }
        return displayedPosts;
    }

    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        if (!followerToFollowees.containsKey(followerId)) {
            followerToFollowees.put(followerId, new LinkedList<>());
        }
        if (!followerToFollowees.get(followerId).contains(followeeId)) {
            followerToFollowees.get(followerId).add(followeeId);
        }
    }

    public void unfollow(int followerId, int followeeId) {
        if (followerToFollowees.containsKey(followerId))
            followerToFollowees.get(followerId).remove((Integer) followeeId);
    }

}
