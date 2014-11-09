$(document).ready(function() {
    var playerId = $('body').data('player-id');
    $.get('/player/' + playerId + '/teams_leader', function(teams) {
        teams.forEach(function(team, index, array) {
            var panel = new LeaderPanel(team);
            panel.formAndAppend();
            panel.setHandlers();
        });
    }, 'json');

    $.get('/player/' + playerId + '/teams_participant', function(teams) {
        teams.forEach(function(team, index, array) {
            var panel = new ParticipantPanel(team);
            panel.formAndAppend();
            panel.setHandlers();
        });
    }, 'json');
});

var LeaderPanel = function(team) {
    var self = this;
    self.team = team;
    self.panelGroup = $('#team-leader-panels');

    self.formAndAppend = function() {
        var panelHTML =
            "<div class='panel panel-default' id='team-" + self.team.id + "'>" +
                "<div class='panel-heading' data-toggle='collapse' data-target='#team-" + self.team.id + "-content'>" +
                    "<h3 class='panel-title'>" + self.team.name + "</div>" +
                "</div>" +
                "<div class='panel panel-collapse collapse' id='team-" + self.team.id + "-content'>" +
                    "<div class='panel-body'>" +
                        "<form role='form' id='team-" + self.team.id + "-form'>" +
                            "<div class='form-group'>" +
                                "<label for='team-name'>Team Name:</label>" +
                                "<input type='text' class='form-control' id='team-" + self.team.id + "-name-input'>" +
                            "</div>" +
                            "<button class='btn btn-default' type='submit'></button>" +
                        "</form>" +
                    "</div>" +
                "</div>" +
            "</div>"

            ;
        self.panelGroup.append(panelHTML);
    };

    self.setHandlers = function() {
        $('#team-' + self.team.id + '-form').on('submit', function(event) {
            $.post('/team/' + self.team.id + '/name', {name: $('#team-' + self.team.id + "-name-input").val()});
        });
    };
};

var ParticipantPanel = function(team) {
    var self = this;
    self.team = team;
    self.panelGroup = $('#team-participant-panels');

    self.formAndAppend = function() {
        var html =
            "<strong>Team Name: </strong>" + self.team.name +
            "<strong>Accept Free Agents: </strong>" + self.team.acceptFreeAgents;
        self.panelGroup.append(panelHTML);
    };

    self.setHandlers = function() {
        //
    };
};
