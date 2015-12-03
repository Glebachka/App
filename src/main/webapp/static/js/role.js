$(function () {

    var currentUserRoleJs = $('#currentUserRole').val();
    if (currentUserRoleJs != 'dba') {
        $('a').hide();
    }
});