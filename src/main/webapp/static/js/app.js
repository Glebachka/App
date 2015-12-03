/**
 * Created by gleb on 24.11.2015.
 */
$(function () {

    var currentUserRoleJs = $('#currentUserRole').val();
    if (currentUserRoleJs != 'dba') {
        $('a').hide();
    }
});