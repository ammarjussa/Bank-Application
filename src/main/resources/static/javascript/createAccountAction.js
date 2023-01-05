$(function() {
    $('#create-account-form').submit(function(event) {
        event.preventDefault();

        var formData = {
            'name': $('input[name=name]').val(),
            'email': $('input[name=email]').val(),
            'password': $('input[name=password]').val()
        };

        $.ajax({
            type: 'POST',
            url: '/create-account',
            data: formData,
            dataType: 'json',
            encode: true
        }).done(function(response) {
            // Successful account creation
            console.log(response);
        }).fail(function(response) {
            // Failed account creation
            console.error(response);
        });
    });
});