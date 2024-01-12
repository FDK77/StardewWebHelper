package com.example.stardewweb.services;

import com.example.stardewweb.models.items.artifacts.Artifacts;
import com.example.stardewweb.parsers.artifacts.ArtifactsParser;
import com.example.stardewweb.parsers.artifacts.ArtifactsStatusParser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
@Service
public class ArtifactsService {
    private List<Artifacts> artifacts;

    public List<Artifacts> initArtifacts() throws IOException {
        ArtifactsParser parser = new ArtifactsParser();
        artifacts = parser.parseJsonFile();
        return artifacts;
    }

    public List<Artifacts> ArtifactsStatus(HttpSession session) throws Exception {
        ArtifactsStatusParser parser = new ArtifactsStatusParser();
        return parser.parseArtifactsStatus(session,artifacts);
    }

    public Artifacts findById(int id) {
        return artifacts.stream()
                .filter(artifact -> artifact.getItemID() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Artifacts> getArtifacts() {
        return artifacts;
    }
}
