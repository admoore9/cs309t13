$(document).ready(function() {
    var bracket = new Bracket($('body').data('tournament-id'));
    bracket.formAndAppendBracket($('#bracket'), $('#tournament-name'));
    
    $("#createTeam").click( function() {
    	window.location.href = "/team";
    });
    
    $("#joinTeam").click( function() {
    	window.location.href = "/survey/" + $('body').data('tournament-id') + "/view";
    });
});

var IS_REFEREE;

var Team = function(id, name) {
    var self = this;
    self.id = id;
    self.name = name;
    self.score = 0;
    self.html = $('<team></team>');
    self.input_box = null;
    self.name_html = null;
};
Team.prototype.getHTML = function() {
    var self = this;
    self.html.children().remove();
    self.name_html = $('<span></span>').text(self.name);
    self.html.append(self.name_html);

    if(!IS_REFEREE) {
        self.html.append($('<score></score>').text(self.score));
    } else {
        self.input_box = $('<input>').width(20).val(self.score);
        var score = $('<score></score>').append(self.input_box);
        self.html.append(score);
    }

    return self.html;
};
Team.prototype.setHandlers = function(game_id) {
    var self = this;
    self.name_html.on('click', function() {
        // Get players from server
        $.get('/team/' + self.id + '/players', function(response) {
            console.log(response);
            var names = [];
            response.forEach(function(player, index, array) {
                names.push(player.name);
            });
            alert(names);
        }, 'json');
    });

    if(IS_REFEREE) {
        self.input_box.on('input', function() {
            console.log(self.input_box.val());
            // Post new score to server
            // $.post('/game/' + game_id + '/update_score', {team_id: self.id, score: self.input_box.val()}, function() {}, 'json');
        });
    }
};

var Game = function(id, nextGame_id, roundNumber, time, location, teams_per_game, teams) {
    var self = this;
    self.id = id;
    self.nextGame_id = nextGame_id;
    self.roundNumber = roundNumber;
    self.time = time;
    self.location = location;
    self.teams_per_game = teams_per_game;
    self.teams = teams;
    self.html = $('<game></game>');
};
Game.prototype.orderTeams = function() {
    var self = this;
    self.teams.sort(function(team1, team2) {
        return team1.id - team2.id;
    });
};
Game.prototype.getHTML = function() {
    var self = this;
    self.html.children().remove();
    self.teams.forEach(function(team, index, aray) {
        self.html.append(team.getHTML());
    });

    // Handle not know what team will advance ye
    for(var i = 0; i < self.teams_per_game - self.teams.length; i++) {
        self.html.append((new Team(-1, '--').getHTML()));
    }
    return self.html;
};
Game.prototype.setHandlers = function() {
    var self = this;
    self.teams.forEach(function(team, index, array) {
        team.setHandlers(self.id);
    });
};

var Round = function(num, games) {
    var self = this;
    self.num = num;
    self.games = games;
    self.html = $('<round></round>');
};
Round.prototype.orderGames = function() {
    var self = this;
    // Sort by nextGame_id then game_id
    self.games.sort(function(game1, game2) {
        var nextGame_diff = game1.nextGame_id - game2.nextGame_id;
        if(nextGame_diff !== 0) {
            return nextGame_diff;
        }
        return game1.id - game2.id;
    });

    // Order the teams in each game
    self.games.forEach(function(game, index, array) {
        game.orderTeams();
    });
};
Round.prototype.getVerticalGap = function(roundNumber) {
    var self = this;

    if(roundNumber == 1) {
        return 40;
    }
    return 2 * self.getVerticalGap(roundNumber - 1) + self.game_height;
};
Round.prototype.getVerticalOffset = function(roundNumber) {
    var self = this;

    if(roundNumber == 1) {
        return 0;
    }
    return self.getVerticalOffset(roundNumber - 1) + 0.5 * (self.game_height + self.getVerticalGap(roundNumber - 1));
};
Round.prototype.setHeights = function() {
    var self = this;

    var game = self.games[0];
    if(game === null || game === undefined) {
        self.left = -1;
        self.vertical_offset = -1;
        self.vertical_gap = -1;
        return;
    }
    var gameHTML = game.getHTML();

    // Append hidden version of element so height and width are available
    gameHTML.hide();
    $('body').append(gameHTML);

    self.game_height = gameHTML.height();
    var game_width = gameHTML.width();

    // Remove the hidden element and the hidden class from the element
    gameHTML.remove();
    gameHTML.show();

    self.left = (self.num - 1) * (game_width + 60);
    self.vertical_offset = self.getVerticalOffset(self.num);
    self.vertical_gap = self.getVerticalGap(self.num);
};
Round.prototype.getHTML = function() {
    var self = this;
    self.html.children().remove();
    self.setHeights();

    self.games.forEach(function(game, index, array) {
        var gameHTML = game.getHTML();
        var top = self.vertical_offset + index * (self.game_height + self.vertical_gap);

        gameHTML.offset({top: top, left: self.left});
        self.html.append(gameHTML);
    });
    return self.html;
};
Round.prototype.setHandlers = function() {
    var self = this;
    self.games.forEach(function(game, index, array) {
        game.setHandlers();
    });
};

var Bracket = function(id) {
    var self = this;
    self.id = id;
    self.name = "";
    self.min_players = 0;
    self.max_players = 0;
    self.teams_per_game = 0;
    self.officials_per_game = 0;
    self.rounds = [];
    self.html = $('<bracket></bracket>');
};
Bracket.prototype.getName = function() {
    return this.name;
};
Bracket.prototype.processTournament = function(tournament) {
    var self = this;
    IS_REFEREE = tournament.is_referee;
    self.name = tournament.name;
    console.log(self.name);
    self.min_players = tournament.minPlayers;
    self.max_players = tournament.maxPlayers;
    self.teams_per_game = tournament.teamsPerGame;
    self.officials_per_game = tournament.officialsPerGame;
    rounds_dict = {};
    tournament.games.forEach(function(elem, index, array) {
        var nextGameId = elem.nextGame !== null ? elem.nextGame.id : elem.nextGame;
        var game = new Game(elem.id, nextGameId, elem.roundNumber, elem.gameTime, elem.gameLocation, tournament.teamsPerGame, []);
        elem.teams.forEach(function(team, index, array) {
            game.teams.push(new Team(team.id, team.name));
        });

        if(rounds_dict[game.roundNumber] !== undefined) {
            rounds_dict[game.roundNumber].push(game);
        } else {
            rounds_dict[game.roundNumber] = [game];
        }
    });

    Object.keys(rounds_dict).forEach(function(elem, index, array) {
        self.rounds.push(new Round(elem, rounds_dict[elem]));
    });
};
Bracket.prototype.orderRounds = function() {
    var self = this;
    self.rounds.sort(function(round1, round2) {
        return round1.num - round2.num;
    });

    self.rounds.forEach(function(round, index, array) {
        round.orderGames();
    });
};
Bracket.prototype.setHandlers = function() {
    var self = this;
    self.rounds.forEach(function(round, index, array) {
        round.setHandlers();
    });
};
Bracket.prototype.formHTML = function() {
    var self = this;
    self.html.children().remove();
    self.rounds.forEach(function(round, index, array) {
        self.html.append(round.getHTML());
    });
    if(self.html.children().length === 0) {
        self.html.text("The bracket for this tournament isn't formed yet.");
    }
};
Bracket.prototype.formAndAppendBracket = function(parent, tournamentNameElement) {
    var self = this;
    $.get('/tournament/' + self.id, function(tournament) {
        self.processTournament(tournament);
        self.orderRounds();
        self.formHTML();
        parent.append(self.html);
        self.setHandlers();
        tournamentNameElement.text(self.name);
    }, 'json');
};
