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
        $('<score></score>').append(self.input_box);
        self.html.append(score);
    }

    return self.html;
};
Team.prototype.setHandlers = function(game_id) {
    var self = this;
    self.name_html.on('click', function() {
        console.log(self.name);
        // Get players from server
        // $.get('/team/' + self.id + '/players', function(response) {
        //     // Form popover or something?
        // });
    });

    if(IS_REFEREE) {
        self.input_box.on('input', function() {
            console.log(self.input_box.val());
            // Post new score to server
            // $.post('/game/' + game_id + '/update_score', {team_id: self.id, score: self.input_box.val()}, function() {}, 'json');
        });
    }
};

var Game = function(id, next_game_id, round_number, time, location, teams_per_game, teams) {
    var self = this;
    self.id = id;
    self.next_game_id = next_game_id;
    self.round_number = round_number;
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
Round.prototype.getVerticalGap = function(round_number) {
    var self = this;

    if(round_number == 1) {
        return 40;
    }
    return 2 * self.getVerticalGap(round_number - 1) + self.game_height;
};
Round.prototype.getVerticalOffset = function(round_number) {
    var self = this;

    if(round_number == 1) {
        return 0;
    }
    return self.getVerticalOffset(round_number - 1) + 0.5 * (self.game_height + self.getVerticalGap(round_number - 1));
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
    self.rounds = [];
    self.html = $('<bracket></bracket>');
};
Bracket.prototype.processTournament = function(tournament) {
    var self = this;
    IS_REFEREE = tournament.is_referee;
    rounds_dict = {};
    tournament.games.forEach(function(elem, index, array) {
        var game = new Game(elem.id, elem.next_game.id, elem.round_number, elem.time, elem.location, tournament.teams_per_game, []);
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
    var self = this;
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
Bracket.prototype.formBracketTest = function() {
    var self = this;
    var tournament = {
        teams_per_game: 2,
        is_referee: false,
        games: [
            {
                id: 0,
                next_game: {id: 2},
                round_number: 1,
                time: 't0',
                location: 'l0',
                teams: [
                    {id: 0, name: 'name0'},
                    {id: 1, name: 'name1'}
                ]
            },
            {
                id: -1,
                next_game: {id: 2},
                round_number: 1,
                time: 't0',
                location: 'l0',
                teams: [
                    {id: -1, name: 'name-1'},
                    {id: 2, name: 'name2'}
                ]
            },
            {
                id: 1,
                next_game: {id: 5},
                round_number: 2,
                time: 't1',
                location: 'l1',
                teams: [
                    {id: 1, name: 'name1'},
                    {id: 2, name: 'name2'}
                ]
            },
            {
                id: 2,
                next_game: {id: 5},
                round_number: 2,
                time: 't2',
                location: 'l2',
                teams: [
                    {id: 3, name: 'name3'},
                    {id: 4, name: 'name4'}
                ]
            },
            {
                id: 3,
                next_game: {id: 6},
                round_number: 2,
                time: 't3',
                location: 'l3',
                teams: [
                    {id: 5, name: 'name5'},
                    {id: 6, name: 'name6'}
                ]
            },
            {
                id: 4,
                next_game: {id: 6},
                round_number: 2,
                time: 't4',
                location: 'l4',
                teams: [
                    {id: 7, name: 'name7'}
                ]
            },
            {
                id: 5,
                next_game: {id: 7},
                round_number: 3,
                time: 't5',
                location: 'l5',
                teams: [
                    {id: 1, name: 'name1'},
                    {id: 4, name: 'name4'}
                ]
            },
            {
                id: 6,
                next_game: {id: 7},
                round_number: 3,
                time: 't6',
                location: 'l6',
                teams: [
                    {id: 6, name: 'name6'},
                    {id: 8, name: 'name8'}
                ]
            },
            {
                id: 7,
                next_game: {id: 0},
                round_number: 4,
                time: 't7',
                location: 'l7',
                teams: [
                    {id: 4, name: 'name4'},
                    {id: 6, name: 'name6'}
                ]
            }
        ]
    };
    self.processTournament(tournament);
    self.orderRounds();
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
};
Bracket.prototype.appendBracket = function(parent) {
    var self = this;
    self.formBracketTest();
    self.formHTML();
    parent.append(self.html);
    self.setHandlers();
};
