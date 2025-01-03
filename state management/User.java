public class User {
    private String username;
    private int commentsCount;
    private int likesCount;
    private int postsCount;
    private int onlineMinutes;


    public User(String username) {
        this.username = username;
        this.commentsCount = 0;
        this.likesCount = 0;
        this.postsCount = 0;
        this.onlineMinutes = 0;
    }

    public void addComment() {
        commentsCount++;
    }

    public void addLike() {
        likesCount++;
    }

    public void addPost() {
        postsCount++;
    }

    public void addOnlineMinutes(int minutes) {
        onlineMinutes += minutes;
    }

    public String getUsername() {
        return username;
    }

    
}
