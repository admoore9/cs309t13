$(document).ready(function() {
    $('#message-form').bootstrapValidator({
        message : 'This value is not valid',
        fields : {
            recipient : {
                validators : {
                    notEmpty : {
                        message : 'At least one recipient must be entered'
                    },
                    remote : {
                        message : 'The recipient does not exist',
                        url : '/mail/doesRecipientExist'
                    }
                }
            },
            subject : {
                validators : {
                    notEmpty : {
                        message : 'A subject is required'
                    },
                    stringLength : {
                        max : 100,
                        message : 'The message subject must be less than 100 characters long'
                    }
                }
            }
        }
    }).on('success.form.bv', function(e) {
        // Stop form from submitting normally
        e.preventDefault();
        var url = "/mail/send";
        $.ajax({
            type : "POST",
            url : url,
            data : $('#message-form').serialize(),
            success : function(result) {
                $('#composeModal').modal('hide');
                location.reload();
            }
        });
    });
    $('body').on('click', '#save-draft', function(e) {
        var draftId = $('#message-form').attr("data-draft-id");
        var url = "/mail/save_draft";
        console.log($('#message-form').serialize() + "&draft_id=" + draftId);
        $.ajax({
            type : "POST",
            url : url,
            data : $('#message-form').serialize() + "&draft_id=" + draftId,
            success : function(result) {
                $('#draft-alert').show();
                $('#message-form').attr("data-draft-id", result);
            }
        });
    });
    $('body').on('click', '.modal-body', function(e) {
        $('#draft-alert').hide();
    });
    $('body').on('click', '.panel-heading', function(e) {
        var url = "/mail/setMessageAsViewed";
        var id = this.getAttribute("data-message-id");
        if (this.className.match(/(?:^|\s)unviewed(?!\S)/)) {
            $.ajax({
                type : "POST",
                url : url,
                data : {
                    messageId : id
                },
                success : function(result) {
                    document.getElementById("heading-" + id).className = "panel-heading collapsed";
                }
            });
        }
    });
    $('body').on('click', '.selection-checkbox', function(e) {
        var selected = $('input:checkbox:checked.selection-checkbox');
        if (selected.length !== 0)
            $( ".selection-icon" ).show();
        else
            $( ".selection-icon" ).hide();
    });
    $('body').on('click', '#delete-message', function(e) {
        var url = "/mail/delete";
        $.ajax({
            type : "POST",
            url : url,
            data : {"messages" : getSelectedMessages()},
            success : function(result) {
                location.reload();
            }
        });
    });
    $('body').on('click', '#mark-unread', function(e) {
        var url = "/mail/mark_unread";
        $.ajax({
            type : "POST",
            url : url,
            data : {"messages" : getSelectedMessages()},
            success : function(result) {
                location.reload();
            }
        });
    });
    $('body').on('click', '#edit-draft', function(e) {
        var id = this.getAttribute("data-draft-id");
        var recipient = this.getAttribute("data-draft-recipient");
        var subject = this.getAttribute("data-draft-subject");
        var body = this.getAttribute("data-draft-body");
        $('#message-form').attr("data-draft-id", id);
        $('#new-message-recipient').val(recipient);
        $('#new-message-subject').val(subject);
        $('#new-message-body').val(body);
        $('#composeModal').modal('show');
    });
    
    function getSelectedMessages() {
        var selected = $('input:checkbox:checked.selection-checkbox');
        var messagesIds = "";
        for (var i = 0; i < selected.length; i++) {
            messagesIds = messagesIds + selected[i].value + ",";
        }
        return messagesIds;
    }
    

});