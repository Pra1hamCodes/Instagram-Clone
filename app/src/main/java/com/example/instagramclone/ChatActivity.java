package com.example.instagramclone;

public class ChatActivity extends AppCompatActivity {
    // Define the layout for the chat activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        // Get the user's name from the intent
        String username = getIntent().getStringExtra("username");
        // Set the title of the activity
        setTitle("Chat with " + username);
        // Create a new chat fragment
        ChatFragment chatFragment = new ChatFragment();
        // Set the arguments for the chat fragment
        Bundle args = new Bundle();
        args.putString("username", username);
        chatFragment.setArguments(args);
        // Add the chat fragment to the activity
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, chatFragment)
                .commit();
    }
    // Create a new fragment for the chat functionality
    public static class ChatFragment extends Fragment {
        // Define the layout for the chat fragment
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_chat, container, false);
        }
        // Get the user's name from the arguments
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            String username = getArguments().getString("username");
            // Set the title of the fragment
            getActivity().setTitle("Chat with " + username);
            // Initialize the chat UI
            // ...
            // Set up the chat functionality
            // ...
        }
    }
}
}
