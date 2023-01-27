package ch.heig.basket.api.endpoints;

import ch.heig.basket.api.exceptions.TeamNotFoundException;
import ch.heig.basket.api.services.TeamService;
import org.openapitools.api.TeamsApi;
import org.openapitools.model.Team;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class TeamEndPoint implements TeamsApi{

    private final TeamService teamService;

    public TeamEndPoint(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public ResponseEntity<List<Team>> getTeams() {
        return ResponseEntity.ok(teamService.getTeams());
    }

    @Override
    public ResponseEntity<Team> getTeam(Integer id) {
        return ResponseEntity.ok(teamService.getTeam(id));
    }

/*
    @Override
    public ResponseEntity<Void> addTeam(Team team) {
        try{
            int t = teamService.addTeam(team);
            return ResponseEntity.created(URI.create("/teams/" + t)).build();
        } catch (TeamNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }
*/
}
