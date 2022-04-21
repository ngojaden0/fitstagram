# fitstagram

Posting:
stored posting objects in database
  Firebase storage -> pictures
  Firebase firestore -> posts
Idea is when calling both posts w/ pictures, do a search for the directory that matches the user_id and then the post_id;
  All data received from the database is read only access (read only, if the wifi cuts out, can't access photos)
General feed:
  access database and organize it into a feed widget
Featured feed
  access general feed database and look for posts with featured=true;
     The idea is likes will determine if be a featured post but we'll see...maybe 3 most recent posts? we present wednesday
Voting
  Jaden: working on that
User Profile
  Justine: has UI, integrate to main page, all users stored in firestore
Ranking
  Christian: integrate to main page
Login
  Bradley: working on it, need for user_id generation
