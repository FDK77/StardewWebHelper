package com.example.stardewweb.services;

import com.example.stardewweb.models.gameOther.Achivements;
import com.example.stardewweb.models.items.artifacts.Artifacts;
import com.example.stardewweb.parsers.Achivements.AchievementStatusParser;
import com.example.stardewweb.parsers.Achivements.AchivementsParser;
import com.example.stardewweb.parsers.artifacts.ArtifactsStatusParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AchivementsService {
    private List<Achivements> achivements;

    public List<Achivements> initAchievements(){
        achivements = AchivementsParser.parse();
        return achivements;
    }

    public List<Achivements> AchievementStatus(HttpSession session) throws Exception {
        AchievementStatusParser parser = new AchievementStatusParser();
        return parser.parseArtifactsStatus(session,achivements);
    }
}
