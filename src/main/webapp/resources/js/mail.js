$(document)
        .ready(
                function() {
                    $('#message-form')
                            .bootstrapValidator(
                                    {
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
                                    }
                                });
                            });
                });