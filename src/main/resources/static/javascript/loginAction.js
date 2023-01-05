$(function() {
    $('#login-form').submit(function(event) {
        event.preventDefault();
        alert('clicked');

        var formData = {
            'username': $('input[name=username]').val(),
            'password': $('input[name=password]').val()
        };

        $.ajax({
            type: 'POST',
            url: '/login',
            data: formData,
            dataType: 'json',
            encode: true
        }).done(function(response) {
            // Successful login
            console.log(response);
        }).fail(function(response) {
            // Failed login
            console.error(response);
        });
    });
});