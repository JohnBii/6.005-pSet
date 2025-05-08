package twitter;

public class UserFollowerCount implements Comparable<UserFollowerCount> {
    final private String username;
    private int followerCount;

    // invariant : there should be only one UserFollowerCount for one username
    public UserFollowerCount(String username, int count) {
        this.username = username;
        followerCount = count;
    }

    public void followerIncrement() {
        followerCount++;
    }

    public int getFollowersCount() {
        return followerCount;
    }

    public String getUserName() {
        return username;
    }

    @Override
    public int compareTo(UserFollowerCount otherUser) {
        if (otherUser.getFollowersCount() == this.getFollowersCount()) {
            return this.getUserName().compareTo(otherUser.getUserName()); // same followerCount but different name, they are different
        } else {
            return otherUser.followerCount - this.followerCount; // make the sortedset a descending one
        }
    }

    public boolean sameUser(String otherUserName) {
        return username.toLowerCase().equals(otherUserName.toLowerCase());
    }

    @Override
    public String toString() {
        return username + " " + followerCount;
    }
    
    @Override
    public boolean equals(Object o) {
        return o instanceof UserFollowerCount && this.sameValue((UserFollowerCount) o);
    }
    
    private boolean sameValue(UserFollowerCount that) {
        return this.username.equals(that.getUserName()) && this.followerCount == that.getFollowersCount();
    }

}
