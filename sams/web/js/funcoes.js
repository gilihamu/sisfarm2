function formataCpfCnpj(objCampo){
    var intTipo;
    var intTamanho;
   // var categoria =document.form1.categoria.options[document.form1.categoria.selectedIndex].value;
    var categoria =document.form1.categoria.options[document.form1.categoria.selectedIndex].value;
   
 
    if (categoria == 'J'){
        intTipo = 13;
        intTamanho = 18;
    }
    else
    if (categoria == 'F')
    {
        intTipo = 5;
        intTamanho = 14;

    }
    var str=objCampo.value

    //Tamanho m�ximo permitido
    if (str.length>intTamanho-1){
        return false
    }

    //Verifica o intTipo para barrar caracteres inv�lidos
    switch (intTipo)
    {
        case 5: //cpf

            if (window.event.keyCode<48 || window.event.keyCode>57){
                return false
            }

            switch (str.length)
            {
                case 3:
                    if (str.length==3){
                        objCampo.value = objCampo.value + '.'
                        return true
                    }

                case 7:
                    if (str.length==7){
                        objCampo.value = objCampo.value + '.'
                        return true
                    }
                case 11:
                    if (str.length==11){
                        objCampo.value = objCampo.value + '-'
                        return true
                    }
                default:
                    return true
            }
        case 13://CNPJ
            if (window.event.keyCode<48 || window.event.keyCode>57){
                return false
            }

            switch (str.length)
            {
                case 2:
                    if (str.length==2){
                        objCampo.value = objCampo.value + '.'
                        return true
                    }

                case 6:
                    if (str.length==6){
                        objCampo.value = objCampo.value + '.'
                        return true
                    }
                case 10:
                    if (str.length==10){
                        objCampo.value = objCampo.value + '/'
                        return true
                    }
                case 15:
                    if (str.length==15){
                        objCampo.value = objCampo.value + '-'
                        return true
                    }

                default:
                    return true
            }
    }


}

function formataCampo(objCampo,intTipo,intTamanho){

    //alert(intTipo);
    //objCampo   = campo a ser formatado
    //intTipo    = 1  -> texto;
    //intTipo    = 2  -> n�mero;
    //intTipo    = 3  -> data;
    //intTipo    = 4  ->texto e numero;
    //intTipo    = 5  ->CPF;
    //intTipo    = 6  -> numero e virgula
    //intTipo    = 7  -> Data Referencia
    //intTipo    = 10 -> hora
    //intTipo    = 11 -> texto e n�mero sem os caracteres especiais (barra, aspas duplas, aspas simples)
    //intTipo    = 12 -> Telefone
    //intTipo    = 13 -> CNPJ
    //intTipo    = 14 -> Cep
    //intTamanho = Quantidade de caracteres que o campo poder� aceitar

    //verifica tecla pressionada
    var str=objCampo.value

    //Tamanho m�ximo permitido
    if (str.length>intTamanho-1){
        return false
    }

    //Verifica o intTipo para barrar caracteres inv�lidos
    switch (intTipo)
    {
        case 1: //texto~
            if (window.event.keyCode==13) {
                return false
            }
            if (window.event.keyCode==32) {
                //Nao permitir a digitacao de 2 espacos em branco
                if (str.substring(str.length-1,str.length)== ' '){
                    return false
                }
                return true
            }
            if (window.event.keyCode>=97 && window.event.keyCode<=122) {
                window.event.keyCode = window.event.keyCode - 32
                return true
            }
            if (window.event.keyCode>=65 && window.event.keyCode<=90) {
                return true
            }
            if (window.event.keyCode>=123 && window.event.keyCode<=255) {
                window.event.keyCode = window.event.keyCode - 32
                return true
            }

            break
        case 2: //n�meros
            if (window.event.keyCode>=48 && window.event.keyCode<=57){
                return true
            }
        case 3: //Data
            if (window.event.keyCode<48 || window.event.keyCode>57){
                return false
            }
            if (str.length==2){
                objCampo.value = objCampo.value + '/'
                return true
            }
            if (str.length==5){
                objCampo.value = objCampo.value + '/'
                return true
            }
            return true


        case 4: //Texto e numero
            if (window.event.keyCode==32) {
                return true
            }
            if (window.event.keyCode>=48 && window.event.keyCode<=57){
                return true
            }
            if (window.event.keyCode>=97 && window.event.keyCode<=122) {
                window.event.keyCode = window.event.keyCode - 32
                return true
            }
            if (window.event.keyCode>=65 && window.event.keyCode<=90) {
                return true
            }

            if (window.event.keyCode>=43 && window.event.keyCode<=59){
                window.event.keyCode = window.event.keyCode - 47
                return true
            }
        case 5: //cpf

            if (window.event.keyCode<48 || window.event.keyCode>57){
                return false
            }

            switch (str.length)
            {
                case 3:
                    if (str.length==3){
                        objCampo.value = objCampo.value + '.'
                        return true
                    }

                case 7:
                    if (str.length==7){
                        objCampo.value = objCampo.value + '.'
                        return true
                    }
                case 11:
                    if (str.length==11){
                        objCampo.value = objCampo.value + '-'
                        return true
                    }
                default:
                    return true
            }

        case 6: //numero e virgula
            if (window.event.keyCode==32) {
                return true
            }
            if (window.event.keyCode==46) {
                return true
            }
            if (window.event.keyCode>=65 && window.event.keyCode<=90) {
                return true
            }

            if (window.event.keyCode>=48 && window.event.keyCode<=57){
                return true
            }
            //if (window.event.keyCode>=65 && window.event.keyCode<=90) {
            //	return true
            //}

            if (window.event.keyCode>=48 && window.event.keyCode<=57){
                return true
            }
        case 7: //Data Referencia
            if (window.event.keyCode<48 || window.event.keyCode>57){
                return false
            }
            if (str.length==2){
                objCampo.value = objCampo.value + '/'
                return true
            }
            return true

        case 8: //n�meros com -
            if (window.event.keyCode>=45 && window.event.keyCode<=57) {
                return true
            }
        case 9: //somente x e X
            if (window.event.keyCode>=48 && window.event.keyCode<=57){
                return false
            }
            if ((window.event.keyCode==120) || (window.event.keyCode==88)) {
                if (window.event.keyCode==120) window.event.keyCode = window.event.keyCode - 32
                return true
            }
        case 10: //hora
            if (window.event.keyCode<48 || window.event.keyCode>57){
                return false
            }
            if (str.length==2){
                objCampo.value = objCampo.value + ':'
                return true
            }
            if (str.length==5){
                objCampo.value = objCampo.value + ':'
                return true
            }
            return true
        default:
            return false
        case 11: //texto e n�mero sem os caracteres especiais (barra, aspas duplas, aspas simples)
            //alert (window.event.keyCode)
            if (window.event.keyCode==32) {
                //Nao permitir a digitacao de 2 espacos em branco
                if (str.substring(str.length-1,str.length)== ' '){
                    return false
                }
                return true
            }
            if (window.event.keyCode==44) {
                return true
            }
            if (window.event.keyCode==45) {//-
                return true
            }
            if (window.event.keyCode==46) {
                return true
            }
            if (window.event.keyCode==59) {//:
                return true
            }
            if (window.event.keyCode==58) {//;
                return true
            }
            if (window.event.keyCode>=48 && window.event.keyCode<=60) {
                window.event.keyCode = window.event.keyCode - 47
                return true
            }
            if (window.event.keyCode>=97 && window.event.keyCode<=122) {
                return true
            }
            if (window.event.keyCode>=65 && window.event.keyCode<=90) {
                return true
            }

            if (window.event.keyCode>=43 && window.event.keyCode<=59){
                window.event.keyCode = window.event.keyCode - 47
                return true
            }

            if (window.event.keyCode>=123 && window.event.keyCode<=255) {
                return true
            }


        case 12: //telefone

            if (window.event.keyCode<48 || window.event.keyCode>57){
                return false
            }

            switch (str.length)
            {
                case 0:
                    if (str.length==0){
                        objCampo.value = objCampo.value + '('
                        return true
                    }

                case 3:
                    if (str.length==3){
                        objCampo.value = objCampo.value + ')'
                        return true
                    }
                case 8:
                    if (str.length==8){
                        objCampo.value = objCampo.value + '-'
                        return true
                    }
                default:
                    return true
            }
        case 13://CNPJ
            if (window.event.keyCode<48 || window.event.keyCode>57){
                return false
            }

            switch (str.length)
            {
                case 2:
                    if (str.length==2){
                        objCampo.value = objCampo.value + '.'
                        return true
                    }

                case 6:
                    if (str.length==6){
                        objCampo.value = objCampo.value + '.'
                        return true
                    }
                case 10:
                    if (str.length==10){
                        objCampo.value = objCampo.value + '/'
                        return true
                    }
                case 15:
                    if (str.length==15){
                        objCampo.value = objCampo.value + '-'
                        return true
                    }

                default:
                    return true
            }
        case 14: //Cep

            if (window.event.keyCode<48 || window.event.keyCode>57){
                return false
            }

            switch (str.length)
            {
                case 5:
                    if (str.length==5){
                        objCampo.value = objCampo.value + '-'
                        return true
                    }
                default:
                    return true
            }
        



    }
    return false
}