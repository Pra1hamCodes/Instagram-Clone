package com.example.instagramclone;

public class LikesActivity {
}
public class LikesFragment extends Fragment {
​
    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter<User, LikesViewHolder> firebaseRecyclerAdapter;
​
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_likes, container, false);
​
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
​
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, LikesViewHolder>(
                User.class,
                R.layout.item_user,
                LikesViewHolder.class,
                FirebaseDatabase.getInstance().getReference().child("users")
        ) {
            @Override
            protected void populateViewHolder(LikesViewHolder viewHolder, User model, int position) {
                viewHolder.setName(model.getName());
                viewHolder.setProfileImage(model.getProfileImageUrl());
            }
        };
​
        recyclerView.setAdapter(firebaseRecyclerAdapter);
​
        return view;
    }
​
    @Override
    public void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();
    }
​
    @Override
    public void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }
​
    public static class LikesViewHolder extends RecyclerView.ViewHolder {
​
        private TextView nameTextView;
        private ImageView profileImageView;
​
        public LikesViewHolder(View itemView) {
            super(itemView);
​
            nameTextView = itemView.findViewById(R.id.name_text_view);
            profileImageView = itemView.findViewById(R.id.profile_image_view);
        }
​
        public void setName(String name) {
            nameTextView.setText(name);
        }
​
        public void setProfileImage(String profileImageUrl) {
            // Load the profile image using a library like Glide or Picasso
        }
    }
}
​



        java
public class MainActivity extends AppCompatActivity {
​
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
​
        // Replace the current fragment with the LikesFragment
        showLikesFragment();
    }
​
    private void showLikesFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new LikesFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
​



        java
public class PostsFragment extends Fragment {
​
    private LikeButton likeButton;
​
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts, container, false);
​
        likeButton = view.findViewById(R.id.like_button);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the current fragment with the LikesFragment
                ((MainActivity) getActivity()).showLikesFragment();
            }
        });
​
        return view;
    }
}
​



        java
public class LikesFragment extends Fragment {
​
    private RecyclerView recyclerView;
    private LikesAdapter adapter;
​
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_likes, container, false);
​
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
​
        adapter = new LikesAdapter();
        recyclerView.setAdapter(adapter);
​
        return view;
    }
}
​



        java
public class LikesAdapter extends RecyclerView.Adapter<LikesViewHolder> {
​
    private List<String> names;
    private List<String> profileImageUrls;
​
    public LikesAdapter() {
        names = new ArrayList<>();
        profileImageUrls = new ArrayList<>();
​
        // Add some sample data
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        profileImageUrls.add("https://example.com/alice.jpg");
        profileImageUrls.add("https://example.com/bob.jpg");
        profileImageUrls.add("https://example.com/charlie.jpg");
    }
​
    @Override
    public LikesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_like, parent, false);
        return new LikesViewHolder(view);
    }
​
    @Override
    public void onBindViewHolder(LikesViewHolder holder, int position) {
        holder.setName(names.get(position));
        holder.setProfileImage(profileImageUrls.get(position));
    }
​
    @Override
    public int getItemCount() {
        return names.size();
    }
}
​



        java
public class LikesViewHolder extends RecyclerView.ViewHolder {
​
    private TextView nameTextView;
    private ImageView profileImageView;
​
    public LikesViewHolder(View itemView) {
        super(itemView);
​
        nameTextView = itemView.findViewById(R.id.name_text_view);
        profileImageView = itemView.findViewById(R.id.profile_image_view);
    }
​
    public void setName(String name) {
        nameTextView.setText(name);
    }
​
    public void setProfileImage(String url) {
        // Use a library like Picasso or Glide to load the image from the URL
        // For example, with Picasso:
        Picasso.get().load(url).into(profileImageView);
    }
}
​



        xml
<!-- fragment_likes.xml -->
<android.support.v7.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
        ​



        xml
<!-- item_like.xml -->
<LinearLayout
    android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal"
            android:padding="8dp">
            ​
<ImageView
        android:id="@+id/profile_image_view"
                android:layout_width="48dp"
                android:layout_height="48dp" />
                ​
<TextView
        android:id="@+id/name_text_view"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginLeft="8dp"
                android:textSize="16sp" />
                ​
</LinearLayout>
