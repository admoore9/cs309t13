var validatorOptions = {
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
            },
            body : {
                validators : {
                    stringLength : {
                        max : 1000,
                        message : 'The message subject must be less than 1000 characters long'
                    }
                }
            }
        }
};

$(document).ready(function() {
    $('#message-form').bootstrapValidator(validatorOptions);
    $('body').on('click', '#send-message', function(e) {
        e.preventDefault();
        $('#message-form').bootstrapValidator(validatorOptions);
        var validator = $('#message-form').data('bootstrapValidator').validate();
        if (validator.$invalidFields.length == 0) {
            var url = "/mail/send";
            var draftId = $("#message-form").attr("data-draft-id");
            $.ajax({
                type : "POST",
                url : url,
                data : $('#message-form').serialize() + "&draft_id=" + draftId,
                success : function(result) {
                    $('#composeModal').modal('hide');
                    document.open();
                    document.write(result);
                    document.close();
                }
            });
        }
    });
    $('body').on('click', '#save-draft', function(e) {
        e.preventDefault();
        $('#message-form').bootstrapValidator(validatorOptions);
        var validator = $('#message-form').data('bootstrapValidator').validate();
        if (validator.$invalidFields.length == 0) {
            var draftId = $('#message-form').attr("data-draft-id");
            var url = "/mail/save_draft";
            $.ajax({
                type : "POST",
                url : url,
                data : $('#message-form').serialize() + "&draft_id=" + draftId,
                success : function(result) {
                    $('#message-form').attr("data-draft-id", result);
                    $('#composeModal').modal('hide');
                }
            });
        }
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