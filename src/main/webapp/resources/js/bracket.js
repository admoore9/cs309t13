var Team = function(id, name) {
    var self = this;
    self.id = id;
    self.name = name;
    var html = $('<team></team>');
};
Team.prototype.getHTML = function() {
    // TODO
};

var Game = function(id, next_game_id, round_number, time, location, teams) {
    var self = this;
    self.id = id;
    self.next_game_id = next_game_id;
    self.round_number = round_number;
    self.time = time;
    self.location = location;
    self.teams = teams;
    var root = $('<game></game>');
};
Game.prototype.orderTeams = function() {
    // TODO
};
Game.prototype.getHTML = function() {
    // TODO
};

var Round = function(num, games) {
    var self = this;
    self.num = num;
    self.games = games;
    self.root = $('<round></round>');
};
Round.prototype.orderGames = function() {
    // TODO
};
Round.prototype.getHTML = function() {
    // TODO
};

var Bracket = function(id) {
    var self = this;
    self.id = id;
    self.rounds = [];
    self.root = $('<bracket></bracket>');
};
Bracket.prototype.processTournament = function(tournament) {
    rounds_dict = {};
    tournament['games'].forEach(function(elem, index, array) {
        game = new Game(elem['id'], elem['next_game']['id'], elem['round_number'], elem['time'], elem['location'], []);
        elem['teams'].forEach(function(team, index, array) {
            game.teams.push(new Team(team['id'], team['name']));
        });

        if(rounds_dict[game.round_number] != null) {
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

    // Last round is already sorted, as there's only one game
    for(var i = self.rounds.length - 1; i >= 0; i--) {
        //
    }
};

Bracket.prototype.formBracket = function() {
    var self = this;
    $.get('/tournament/' + self.id, function(tournament) {
        self.processTournament(tournament);
        self.orderRounds();
    }, 'json');
};

Bracket.prototype.getBracket = function() {
    return root;
};
