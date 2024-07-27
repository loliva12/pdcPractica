function buscar() {
    cleanError();

    document.getElementById('iList').innerHTML = '';

    var genero = document.getElementById('genero').value

    if(genero !== ""){
        fetch('./buscar.jsp', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                'genero': genero
            })
        }).then(res => {
            if (res.ok) {
                return res.text();
            }
            throw Error(res.statusText || 'Error del servidor - buscar');
        }).then(html => {

            document.getElementById('iList').insertAdjacentHTML('afterbegin', html);
        }).catch(err => {
            showError(err.message);
        });
    }
}

function save(nroMaterial) {
    cleanError();

    var formulario = document.getElementById('iForm');
    if (!formulario.checkValidity()) {
        formulario.reportValidity();
        return;
    }

    fetch('./save.jsp', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            'correo': document.getElementById('email').value,
            'telefono': document.getElementById('telefono').value,
            'nroLegAlumno': document.getElementById('legajo').value,
            'nroMaterial': nroMaterial
        })
    }).then(res => {
        if (res.ok) {
            return res.text();
        }
        throw Error(res.statusText || 'Error del servidor - save');
    }).then(html => {
        document.getElementById('voto'+nroMaterial).innerHTML = '';
        document.getElementById('voto'+nroMaterial).innerHTML = html;

    }).catch(err => {
        showError(err.message);
    });
}


function showError(message) {
    document.getElementById('iError').innerHTML = message;
    document.getElementById('iError').classList.remove('d-none');
    executeScripts('iError');
}

function cleanError() {
    document.getElementById('iError').classList.add('d-none');
    document.getElementById('iError').innerHTML = '';
}

function executeScripts(containerId) {
    //Contenedor de los scripts a ejecutar
    const container = document.getElementById(containerId);

    //Obtengo los scripts del contenedor
    const scripts = container.querySelectorAll('script');
    scripts.forEach(script => {
        //Si el script tiene un atributo 'src', crea un nuevo script para cargar y ejecutar
        if (script.src) {
            const newScript = document.createElement('script');
            newScript.src = script.src;
            document.head.appendChild(newScript);
        } else {
            // Si no tiene 'src', es un script inline y se puede ejecutar directamente
            eval(script.innerText);
        }
    });
}