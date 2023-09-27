package com.example.instagramclone;

public class ShareActivity extends AppCompatActivity {
    // Define the layout for the sharing activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        // Get the image URI from the intent
        Uri imageUri = getIntent().getParcelableExtra("imageUri");
        // Create a new Intent to share the image
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        // Start the sharing activity
        startActivity(shareIntent);
    }
}
}
