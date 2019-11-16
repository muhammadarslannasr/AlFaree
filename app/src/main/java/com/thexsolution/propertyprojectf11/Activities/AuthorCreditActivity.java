package com.thexsolution.propertyprojectf11.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.thexsolution.propertyprojectf11.Adapters.CreditsRecyclerViewAdapter;
import com.thexsolution.propertyprojectf11.Model.Credit;
import com.thexsolution.propertyprojectf11.R;

import java.util.ArrayList;
import java.util.List;

public class AuthorCreditActivity extends AppCompatActivity {
    private RecyclerView credit_authorRecyclerViewID;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    private CreditsRecyclerViewAdapter creditsRecyclerViewAdapter;
    private List<Credit> creditList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_credit);
        credit_authorRecyclerViewID = findViewById(R.id.credit_authorRecyclerViewIDProperty);
        creditList = new ArrayList<>();
        getSupportActionBar().setTitle("Attribution");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        creditList.add(new Credit("User free icon","https://www.flaticon.com/free-icon/user_149452#term=profile&page=1&position=4","https://creativecommons.org/licenses/by/3.0/","Images made by Freepik(freepik.com)","Creative Commons (Attribution 3.0 Unported)"));
        creditList.add(new Credit("Star free icon","https://www.flaticon.com/free-icon/star_126482#term=star&page=1&position=6","https://creativecommons.org/licenses/by/3.0/","Images made by Freepik(freepik.com) from Flaticon(flaticon.com)","Creative Commons (Attribution 3.0 Unported)"));
        creditList.add(new Credit("Share free icon","https://www.flaticon.com/free-icon/share_126495#term=share&page=1&position=8","https://creativecommons.org/licenses/by/3.0/","Images made by Freepik(freepik.com) from Flaticon(flaticon.com)","Creative Commons (Attribution 3.0 Unported)"));
        creditList.add(new Credit("Logout free icon","https://www.flaticon.com/free-icon/logout_450387#term=exit&page=1&position=15","https://creativecommons.org/licenses/by/3.0/","Images made by Freepik(freepik.com) from Flaticon(flaticon.com)","Creative Commons (Attribution 3.0 Unported)"));
        creditList.add(new Credit("Order free icon","https://www.flaticon.com/free-icon/order_169237#term=attribute&page=1&position=6","https://creativecommons.org/licenses/by/3.0/","Images made by Freepik(freepik.com) from Flaticon(flaticon.com)","Creative Commons (Attribution 3.0 Unported)"));
        creditList.add(new Credit("Search free icon","https://www.flaticon.com/free-icon/search_321830#term=search&page=1&position=77","https://creativecommons.org/licenses/by/3.0/","Icons made by Vectors Market from Flaticon (Flaticon.com)","Creative Commons (Attribution 3.0 Unported)"));
        creditList.add(new Credit("Search free icon","https://www.flaticon.com/free-icon/search_122932#term=search&page=2&position=23","https://creativecommons.org/licenses/by/3.0/","Images made by Freepik(freepik.com) from Flaticon(flaticon.com)","Creative Commons (Attribution 3.0 Unported)"));
        creditList.add(new Credit("Survey free icon","https://www.flaticon.com/free-icon/survey_172168#term=survey&page=1&position=21","https://creativecommons.org/licenses/by/3.0/","Images made by Freepik(freepik.com) from Flaticon(flaticon.com)","Creative Commons (Attribution 3.0 Unported)"));
        creditList.add(new Credit("Banking free icon","https://www.flaticon.com/free-icon/banking_265741#term=sale&page=1&position=20","https://creativecommons.org/licenses/by/3.0/","Images made by Freepik(freepik.com) from Flaticon(flaticon.com)","Creative Commons (Attribution 3.0 Unported)"));
        creditList.add(new Credit("Sale free icon","https://www.flaticon.com/free-icon/sale_1041894#term=sale&page=1&position=18","https://creativecommons.org/licenses/by/3.0/","Images made by Freepik(freepik.com) from Flaticon(flaticon.com)","Creative Commons (Attribution 3.0 Unported)"));
        creditList.add(new Credit("Reminder free icon","https://www.flaticon.com/free-icon/reminder_735200#term=reminder&page=1&position=69","https://creativecommons.org/licenses/by/3.0/","Images made by Freepik(freepik.com) from Flaticon(flaticon.com)","Creative Commons (Attribution 3.0 Unported)"));
        creditList.add(new Credit("Sale time free icon","https://www.flaticon.com/free-icon/sale-time_650875#term=reminder&page=2&position=10","https://creativecommons.org/licenses/by/3.0/","Images made by Freepik(freepik.com) from Flaticon(flaticon.com)","Creative Commons (Attribution 3.0 Unported)"));
        creditList.add(new Credit("House free icon","https://www.flaticon.com/free-icon/house_1670080#term=house&page=1&position=83","https://creativecommons.org/licenses/by/3.0/","Images made by Freepik(freepik.com) from Flaticon(flaticon.com)","Creative Commons (Attribution 3.0 Unported)"));
        creditList.add(new Credit("Home free icon","https://www.flaticon.com/free-icon/home_263115#term=home&page=1&position=1","https://creativecommons.org/licenses/by/3.0/","Images made by Freepik(freepik.com) from Flaticon(flaticon.com)","Creative Commons (Attribution 3.0 Unported)"));
        creditList.add(new Credit("Comment white oval bubble free icon","https://www.flaticon.com/free-icon/comment-white-oval-bubble_25663#term=chat&page=1&position=55","https://creativecommons.org/licenses/by/3.0/","Images made by Dave Gandy from Flaticon(flaticon.com)","Creative Commons (Attribution 3.0 Unported)"));
        creditList.add(new Credit("Magnifier free icon","https://www.flaticon.com/free-icon/magnifier_34202#term=search&page=1&position=5","https://creativecommons.org/licenses/by/3.0/","Images made by SimpleIcon from Flaticon(flaticon.com)","Creative Commons (Attribution 3.0 Unported)"));
        creditList.add(new Credit("Survey free icon","https://www.flaticon.com/free-icon/survey_813414#term=survey&page=1&position=5","https://creativecommons.org/licenses/by/3.0/","Images made by Those Icons from Flaticon(flaticon.com)","Creative Commons (Attribution 3.0 Unported)"));
        creditList.add(new Credit("Alarm Bell free icon","https://www.flaticon.com/free-icon/alarm-bell_78332#term=reminder&page=1&position=27","https://creativecommons.org/licenses/by/3.0/","Images made by Nice and Serious from Flaticon(flaticon.com)","Creative Commons (Attribution 3.0 Unported)"));

        recyclerViewLayoutManager = new GridLayoutManager(AuthorCreditActivity.this, 1);

        credit_authorRecyclerViewID.setLayoutManager(recyclerViewLayoutManager);

        creditsRecyclerViewAdapter = new CreditsRecyclerViewAdapter(AuthorCreditActivity.this, creditList);

        credit_authorRecyclerViewID.setAdapter(creditsRecyclerViewAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AuthorCreditActivity.this.finish();
    }
}
