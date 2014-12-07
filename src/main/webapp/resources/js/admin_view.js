$(document).ready(function() {
    $('.date').datetimepicker({
        format: 'YYYY-MM-DD',
        pickTime: false
    });

    $('#create-tournament-form').on('submit', function(event) {
        event.preventDefault();
        $.post('/tournament/create', {
            name: $('#create-tournament-name-input').val(),
            minPlayersPerTeam: $('#create-tournament-min-players-input').val(),
            maxPlayersPerTeam: $('#create-tournament-max-players-input').val(),
            teamsPerGame: $('#create-teams-per-game-input').val(),
            officialsPerGame: $('#create-officials-per-game-input').val(),
            registrationStart: $('#create-registration-start-date-input').val(),
            registrationClose: $('#create-registration-close-date-input').val(),
            success: function(result) {
                window.location.href = "/admin";
            },
        });
    });
    
    $(".newUserTypeBtn").click(function() {
        $("#newUserType").val($(this).val());
    });
    
    $('#promote-demote-form').submit ( function (e) {
        e.preventDefault();
        var url = "/profile/promote";
        $.post(url, {
            username: $('#username').val(),
            newUserType: $('#newUserType').val()
        }, function() {
            location.reload(true);
        });
    });

    var TournamentById = function() {
        // Get the current state of this to prevent issues
        var self = this;
        self.gotoForm = $('#goto-tournament-form');
        self.inputBox = $('#update-tournament-id-input');
        self.updateForm = $('#update-tournament-form');
        self.tournamentName = $('#update-tournament-name-input');
        self.minPlayers = $('#update-tournament-min-players-input');
        self.maxPlayers = $('#update-tournament-max-players-input');
        self.teamsPerGame = $('#update-teams-per-game-input');
        self.officialsPerGame = $('#update-officials-per-game-input');
        self.registrationStart = $('#update-registration-start-date-input');
        self.registrationClose = $('#update-registration-close-date-input');

        // Causes new values to be fetched from the server when the input boxes value is changed.
        self.setInputHandler = function() {
            self.inputBox.on('input', self.getDataUpdateUI);
            self.gotoForm.on('submit', self.handleGoToTournament);
            self.updateForm.on('submit', self.updateTournament);
        };

        self.getDataUpdateUI = function()  {
            var id = self.inputBox.val();
            $.get('/tournament/' + id, "", self.handleData, "json");
        };

        self.handleData = function(response) {
            self.tournamentName.val(response.name);
            self.minPlayers.val(response.minPlayers);
            self.maxPlayers.val(response.maxPlayers);
            self.teamsPerGame.val(response.teamsPerGame);
            self.officialsPerGame.val(response.officialsPerGame);
            self.registrationStart.val(response.registrationStart);
            self.registrationClose.val(response.registrationClose);
        };

        self.updateTournament = function(event) {
            event.preventDefault();
            $.post('/tournament/' + self.inputBox.val() + '/update', {
                name: self.tournamentName.val(),
                minPlayersPerTeam: self.minPlayers.val(),
                maxPlayersPerTeam: self.maxPlayers.val(),
                teamsPerGame: self.teamsPerGame.val(),
                officialsPerGame: self.officialsPerGame.val(),
                registrationStart: self.registrationStart.val(),
                registrationClose: self.registrationClose.val()
            });
        };

        self.handleGoToTournament = function(event) {
            event.preventDefault();
            alert(window.location.href);
            window.location.href = '/tournament/' + self.inputBox.val() + '/view';
        };
    };

    var tournamentById = new TournamentById();
    tournamentById.setInputHandler();

});
