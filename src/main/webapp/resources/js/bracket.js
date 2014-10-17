var Team = function() {
    // TODO
    var html = $('<team></team>');
};
Team.prototype.getHTML = function() {
    // TODO
};

var Game = function(id, teams) {
    var self = this;
    self.teams = teams;
    var html = $('<game></game>');
};
Game.prototype.getHTML = function() {
    // TODO
};

var Round = function(games) {
    var self = this;
    self.games = games;
};

var Bracket = function(id) {
    var self = this;
    self.id = id;
    self.root = $('<bracket></bracket>');
    self.games = [];
    self.rounds = {};
};
Bracket.prototype.processGames = function(tournament) {
    for(var i = 0; i < tournament['games'].length; i++) {
        var game = tournament['games'][i];
        var roundNumber = game['roundNumber'];

        // Replace next game chain with next_game id for ease
        game['nextGame'] = game['nextGame']['id'];
        self.games.push(game);

        if(self.games[roundNumber] != null) {
            self.rounds[roundNumber].push(game);
        } else {
            self.rounds[roundNumber] = [game];
        }
    }
};
Bracket.prototype.groupGamesInRound = function(roundNumber) {
    var groupedGamesDict = {};
    var roundGames = selfl.rounds[roundNumber];
    for(var i = 0; i < roundGames.length; i++) {
        var game = roundGames[i];
        var nextGame = game['nextGame'];

        if(groupedGamesDict[nextGame] != null) {
            groupedGamesDict[nextGame].push(game);
        } else {
            groupedGamesDict[nextGame] = [game];
        }
    }

    var nextGameIds = Object.keys(groupedGamesDict);
    nextGameIds.sort();
    var groupedGames = [];
    for(var i = 0; i < nextGameIds.length; i++) {
        groupedGames.push(groupedGamesDict[nextGameIds[i]]);
    }
    return groupedGames;
};
Bracket.prototype.formBracket = function() {
    var self = this;
    // $.get('/tournament/' + self.id + '/get_rounds', function(response) {
    //     // TODO
    // }, 'json');
    $.get('/tournament/' + self.id, function(tournament) {
        self.processGames(tournament);
        var groupedFirstRound = self.groupGamesInRound(1);
    }, 'json');
};

Bracket.prototype.getBracket = function() {
    return root;
}
