/**
 * Created by claudio on 24-10-14.
 */
var PuntoVenta = {
    Menu: {
        inicio: function () {
            this.marcarActivo(location.href);
        },
        marcarActivo: function (url) {
            $('ul.page-sidebar-menu li').each(function(idx, li) {
                if (li.className == '') {
                    var link = $(li).find('a').attr('href');
                    if (link != '' && url.search(link) != -1) {
                        $(li).addClass('active');
                        $(li).find('span:last').addClass('selected');
                    }
                }
            });
        }
    }
}