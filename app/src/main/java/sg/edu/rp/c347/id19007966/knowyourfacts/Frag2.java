package sg.edu.rp.c347.id19007966.knowyourfacts;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.crazyhitty.chdev.ks.rssmanager.*;

import java.util.List;
import java.util.Random;

public class Frag2 extends Fragment implements RssReader.RssCallback {

    Button btnChange1, btnChangeRSS;
    RssReader rssReader;
    TextView tvTitle, tvContents;

    public Frag2() {
        // Required empty public constructor
        rssReader = new RssReader(this);
        loadFeeds();
    }

    void loadFeeds() {
        String[] rssURL = {"https://www.channelnewsasia.com/rssfeeds/8395986", "https://www.singstat.gov.sg/rss"};
        rssReader.loadFeeds(rssURL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_frag2, container, false);

        btnChange1 = (Button) view.findViewById(R.id.btnChange1);
        btnChangeRSS = view.findViewById(R.id.btnChangeRSS);
        tvTitle = view.findViewById(R.id.textViewTitle2);
        tvContents = view.findViewById(R.id.textViewContent2);

        btnChange1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                view.setBackgroundColor(Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
                btnChange1.setBackgroundColor(Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
                btnChangeRSS.setBackgroundColor(Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
            }
        });

        btnChangeRSS.setOnClickListener((View v) -> {
            loadFeeds();
        });

        return view;
    }

    @Override
    public void rssFeedsLoaded(List<RSS> rssList) {
        RSS randomRSSFeed = rssList.get(new Random().nextInt(rssList.size()));
        List<Channel.Item> items = randomRSSFeed.getChannel().getItems();
        Channel.Item item = items.get(new Random().nextInt(items.size()));
        String title = item.getTitle();
        String description = item.getDescription();
        tvTitle.setText(title);
        tvContents.setText(description);

    }

    @Override
    public void unableToReadRssFeeds(String errorMessage) {
        Log.e("rssFail", errorMessage);
    }
}