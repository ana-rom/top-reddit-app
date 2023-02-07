package com.vrgsoft.TopRedditApp;

import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.vgrsoft.topRedditApp.R;
import com.vrgsoft.TopRedditApp.dto.ChildDto;
import com.vrgsoft.TopRedditApp.dto.TopPostDto;
import com.vrgsoft.TopRedditApp.dto.mapper.TopPublicationMapper;
import com.vrgsoft.TopRedditApp.model.TopPublication;
import com.vrgsoft.TopRedditApp.service.TopPublicationService;
import com.vrgsoft.TopRedditApp.adapter.TopPublicationAdapter;
import com.vrgsoft.TopRedditApp.util.ServiceGenerator;
import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final TopPublicationService topPublicationService =
            ServiceGenerator.createService(TopPublicationService.class);
    private static final String DEFAULT_AFTER_PARAMETER = "";
    private final TopPublicationMapper mapper = new TopPublicationMapper();
    private TopPostDto topPostDto;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callRedditApi(DEFAULT_AFTER_PARAMETER);

        button = findViewById(R.id.button);
        button.setOnClickListener(b -> {
            if (topPostDto == null) {
                callRedditApi(DEFAULT_AFTER_PARAMETER);
            } else {
                callRedditApi(topPostDto.getTopDataDto().getAfter());
            }
        });
    }

    private void callRedditApi(String after) {
        Call<TopPostDto> topPostDtoCall = topPublicationService.getTopPublications(after);

        topPostDtoCall.enqueue(new Callback<TopPostDto>() {
            @Override
            public void onResponse(@NotNull Call<TopPostDto> call,
                                   @NotNull Response<TopPostDto> response) {
                topPostDto = response.body();

                try {
                    List<TopPublication> topPublications =
                            topPostDto.getTopDataDto().getChildren()
                            .stream()
                            .map(ChildDto::getChildDataDto)
                            .map(mapper::toModel)
                            .collect(Collectors.toList());
                    setTopPublicationRecycler(topPublications);
                } catch (NullPointerException e) {
                    Toast.makeText(getApplicationContext(),"NullPointerException occurs "
                            + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<TopPostDto> call, @NotNull Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setTopPublicationRecycler(List<TopPublication> topPublications) {
        RecyclerView topRecyclerView = findViewById(R.id.recyclerView);
        topRecyclerView.setHasFixedSize(true);
        topRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        TopPublicationAdapter adapter = new TopPublicationAdapter(this, topPublications);
        topRecyclerView.setAdapter(adapter);
    }
}
