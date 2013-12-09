$(document).ready(function () {
    var token;

    $('#login').click(function () {
        var username = $('#username').val();
        var password = $('#password').val();
        var url = 'http://TOMMO/ProgettoSicurezzaWeb/api/Token?username=' + username + '&password=' + password;

        $.ajax({
            url: url,
            type: "GET",
            contentType: "application/json",
            success: function (result) {
                token = result;
                $.mobile.changePage('#productlist');
            },
            error: function () {
                alert('Autenticazione non riuscita');
            }
        });
    });
    $(document).delegate('#productlist', 'pageshow', function () {
        var url = 'http://TOMMO/ProgettoSicurezzaWeb/api/product/';
        $('#listaprod').html('');
        $.ajax({
            headers: { "token": token },
            url: url,
            type: "GET",
            contentType: "application/json",
            success: function (result) {
                $.each(result, function (index, p) {
                    $('#listaprod').append('<li class="dett" data-id="' + p.Id + '"+>id: ' + p.Id + ' nome: ' + p.Nome + '</li>');
                });
                $('#listaprod').listview('refresh');
                $('.dett').click(function () {
                    $.mobile.changePage('#productdetail');
                    var prod;
                    url = 'http://TOMMO/ProgettoSicurezzaWeb/api/product?id=' + $(this).data('id');
                    $.ajax({
                        headers: { "token": token },
                        url: url,
                        type: "GET",
                        contentType: "application/json",
                        success: function (result) {
                            prod = result;
                            $('#proddet').html('<h4>Id: ' + result.Id + '<br>Nome:'
                                + result.Nome + '<br>Descrizione: ' + result.Descrizione);
                        },
                        error: function () {
                            alert('Connessione non riuscita, ripetere il login');
                            $.mobile.changePage('#page.login');
                        }
                    });
                    $('#compra').off();
                    $('#compra').on('click', function () {
                        var prodcart = new Object();
                        prodcart.Id = null;
                        prodcart.Id_Prodotto = prod.Id;
                        if ($('#number').val() == '') {
                            alert('Il campo quantità non puo essere vuoto');

                        } else {
                            prodcart.Qta = $('#number').val();
                            var prodcartjson = JSON.stringify(prodcart);

                            $.ajax({
                                headers: { "token": token },
                                url: "http://TOMMO/ProgettoSicurezzaWeb/api/cart",
                                type: "POST",
                                data: prodcartjson,
                                cache: false,
                                dataType: "json",
                                contentType: "application/json",
                                success: function (result) {
                                    $.mobile.changePage('#productlist');
                                },
                                error: function () {
                                    alert('non inserito');
                                }
                            });
                        }

                    });
                });
            },
            error: function () {
                alert('Connessione non riuscita, ripetere il login');
                $.mobile.changePage('#page.login');
            }
        });
    });

    $(document).delegate('#carrello', 'pageshow', function () {
        var url = 'http://TOMMO/ProgettoSicurezzaWeb/api/cart/';
        $('#listcart').html('');
        $.ajax({
            headers: { "token": token },
            url: url,
            type: "GET",
            contentType: "application/json",
            success: function (result) {
                $.each(result, function (index, p) {
                    url = 'http://TOMMO/ProgettoSicurezzaWeb/api/product?id=' + p.Id_Prodotto;
                    $.ajax({
                        headers: { "token": token },
                        url: url,
                        type: "GET",
                        contentType: "application/json",
                        success: function (result) {
                            $('#listcart').append('<li class="modifica" data-id="'
                                + result.Id + '"data-nome="'
                                + result.Nome + '"data-descrizione="'
                                + result.Descrizione + '"data-qta="'
                                + p.Qta + '">id: ' + result.Id + ' nome: ' + result.Nome + ' quanità: '
                                + p.Qta + '</li>');
                            $('#listcart').listview('refresh');
                            $('.modifica').off();
                            $('.modifica').click(function () {
                                //alert($(this).data('id'));
                                $.mobile.changePage('#productcartdetail');
                                $('#prodcartdet').html('<h4 data-id="'
                                + $(this).data('id') + '"data-nome="'
                                + $(this).data('nome') + '"data-descrizione="'
                                + $(this).data('descrizione') + '"data-qta="'
                                + $(this).data('qta') + '">Id: ' + $(this).data('id') + '<br>Nome:'
                                    + $(this).data('nome') + '<br>Descrizione: ' + $(this).data('descrizione')
                                    + '<br>Quantità: </h4>');
                                var prodcart = new Object();
                                prodcart.Id = null;
                                prodcart.Id_Prodotto = $(this).data('id');
                                $('#quantita').val($(this).data('qta'));
                                $('#modifica').off();
                                $('#modifica').on('click', function () {
                                    //alert(prodcart.Id_Prodotto);
                                    if ($('#quantita').val() == '') {
                                        alert('Il campo quantità non può essere vuoto')

                                    } else {
                                        prodcart.Qta = $('#quantita').val();
                                        var prodcartjson = JSON.stringify(prodcart);
                                        $.ajax({
                                            headers: { "token": token },
                                            url: "http://TOMMO/ProgettoSicurezzaWeb/api/cart",
                                            type: "POST",
                                            data: prodcartjson,
                                            cache: false,
                                            dataType: "json",
                                            contentType: "application/json",
                                            success: function (result) {
                                                $.mobile.changePage('#carrello');
                                            },
                                            error: function () {
                                                alert('non modificato');
                                            }
                                        });
                                    }
                                });

                                $('#cancella').off();
                                $('#cancella').on('click', function () {
                                    // alert(prodcart.Id_Prodotto);
                                    var prodcartjson = JSON.stringify(prodcart);

                                    $.ajax({
                                        headers: { "token": token },
                                        url: "http://TOMMO/ProgettoSicurezzaWeb/api/cart",
                                        type: "DELETE",
                                        data: prodcartjson,
                                        cache: false,
                                        dataType: "json",
                                        contentType: "application/json",
                                        success: function (result) {
                                            $.mobile.changePage('#carrello');
                                        },
                                        error: function () {
                                            alert('non cancellato');
                                        }
                                    });
                                });

                            })
                        },
                        error: function () {
                            alert('Connessione non riuscita, ripetere il login');
                            $.mobile.changePage('#page.login');
                        }
                    });
                });
            },
            error: function () {
                alert('Connessione non riuscita, ripetere il login');
                $.mobile.changePage('#page.login');
            }
        });
    });
})