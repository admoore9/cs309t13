var Team = function(id, name) {
    var self = this;
    self.id = id;
    self.name = name;
    self.html = $('<team></team>');
};
Team.prototype.getHTML = function() {
    self.html.children().remove();
    self.html.text(self.name);
    return self.html;
};

var Game = function(id, next_game_id, round_number, time, location, teams) {
    var self = this;
    self.id = id;
    self.next_game_id = next_game_id;
    self.round_number = round_number;
    self.time = time;
    self.location = location;
    self.teams = teams;
    self.html = $('<game></game>');
};
Game.prototype.orderTeams = function() {
    self.teams.sort(function(team1, team2) {
        return team1.id - team2.id;
    });
};
Game.prototype.getHTML = function() {
    self.html.children().remove();
    self.teams.forEach(function(team, index, aray) {
        self.html.append(team.getHTML());
    });
    return self.html;
};

var Round = function(num, games) {
    var self = this;
    self.num = num;
    self.games = games;
    self.html = $('<round></round>');
};
Round.prototype.orderGames = function() {
    // Sort by next_game_id then game_id
    self.games.sort(function(game1, game2) {
        var next_game_diff = game1.next_game_id - game2.next_game_id;
        if(next_game_diff !== 0) {
            return next_game_diff;
        }
        return game1.id - game2.id;
    });

    // Order the teams in each game
    self.games.forEach(function(game, index, array) {
        game.orderTeams();
    });
};
Round.prototype.getHTML = function() {
    self.html.children().remove();
    self.games.forEach(function(game, index, array) {
        self.html.append(game.getHTML());
    });
    return self.html;
};

var Bracket = function(id) {
    var self = this;
    self.id = id;
    self.rounds = [];
    self.html = $('<bracket></bracket>');
};
Bracket.prototype.processTournament = function(tournament) {
    rounds_dict = {};
    tournament.games.forEach(function(elem, index, array) {
        var game = new Game(elem.id, elem.next_game.id, elem.round_number, elem.time. elem.location, []);
        elem.teams.forEach(function(team, index, array) {
            game.teams.push(new Team(team.id, team.name));
        });

        if(rounds_dict[game.round_number] !== undefined) {
            rounds_dict[game.round_number].push(game);
        } else {
            rounds_dict[game.round_number] = [game];
        }
    });

    Object.keys(rounds_dict).forEach(function(elem, index, array) {
        self.rounds.push(new Round(elem, rounds_dict[elem]));
    });
};
Bracket.prototype.orderRounds = function() {
    self.rounds.sort(function(round1, round2) {
        return round1.num - round2.num;
    });

    self.rounds.forEach(function(round, index, array) {
        round.orderGames();
    });
};

Bracket.prototype.formBracket = function() {
    var self = this;
    $.get('/tournament/' + self.id, function(tournament) {
        self.processTournament(tournament);
        self.orderRounds();
    }, 'json');
};

Bracket.prototype.getBracket = function() {
    self.html.children().remove();
    self.rounds.forEach(function(round, index, array) {
        self.html.append(round.getHTML());
    });
    return self.html;
};
