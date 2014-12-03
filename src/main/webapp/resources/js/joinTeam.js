function submitTeamForm(teamId) {
        //$('#join-team' + teamId + '-form').on('submit', function(event) {
            console.log(teamId);
            //event.preventDefault();
            
            var url = "/team/" + teamId + "/joinTeam";
            var data = $('#join-team' + teamId + '-form').serialize();
            $.ajax({
                type: "POST",
                url: url,
                data: data,
                success: function(data) {
                    window.location.href = "/profile";
                }
            });
    }

$( document ).ready(function() {
   
    
    
    
    
});