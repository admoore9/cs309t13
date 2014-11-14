/**
 * 
 */
$(document).ready(function() {
    $('#create-team-form').on('submit', function(event) {
        event.preventDefault();
        $.post('/team/create', {
            name: $('#create-team-name-input').val(),
            tournamentId: $('#create-team-tournamentId-input').val(),
            invitedPlayerId: $('#create-team-invitedPlayerId-input').val(),
        });
    });

//    var TeamById = function() {
//        // Get the current state of this to prevent issues
//        var self = this;
//        self.gotoForm = $('#goto-tournament-form');
//        self.inputBox = $('#update-tournament-id-input');
//        self.updateForm = $('#update-tournament-form');
//        self.tournamentName = $('#update-tournament-name-input');
//        self.minPlayers = $('#update-tournament-min-players-input');
//        self.maxPlayers = $('#update-tournament-max-players-input');
//        self.teamsPerGame = $('#update-teams-per-game-input');
//        self.officialsPerGame = $('#update-officials-per-game-input');
//
//        // Causes new values to be fetched from the server when the input boxes value is changed.
//        self.setInputHandler = function() {
//            self.inputBox.on('input', self.getDataUpdateUI);
//            self.gotoForm.on('submit', self.handleGoToTournament);
//            self.updateForm.on('submit', self.updateTournament);
//        };
//
//        self.getDataUpdateUI = function()  {
//            var id = self.inputBox.val();
//            $.get('/tournament/' + id, "", self.handleData, "json");
//        };
//
//        self.handleData = function(response) {
//            self.tournamentName.val(response.name);
//            self.minPlayers.val(response.minPlayers);
//            self.maxPlayers.val(response.maxPlayers);
//            self.teamsPerGame.val(response.teamsPerGame);
//            self.officialsPerGame.val(response.officialsPerGame);
//        };
//
//        self.updateTournament = function(event) {
//            event.preventDefault();
//            $.post('/tournament/' + self.inputBox.val() + '/update', {
//                name: self.tournamentName.val(),
//                minPlayersPerTeam: self.minPlayers.val(),
//                maxPlayersPerTeam: self.maxPlayers.val(),
//                teamsPerGame: self.teamsPerGame.val(),
//                officialsPerGame: self.officialsPerGame.val()
//            });
//        };
//
//        self.handleGoToTournament = function(event) {
//            event.preventDefault();
//            alert(window.location.href);
//            window.location.href = '/tournament/' + self.inputBox.val() + '/view';
//        };
//    };
//
//    var tournamentById = new TournamentById();
//    tournamentById.setInputHandler();

});
