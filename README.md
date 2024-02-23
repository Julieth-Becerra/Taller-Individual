
## Información General

- **Puerto:** 3001
- **Documentación de Swagger:** [http://localhost:3001/docs](http://localhost:3001/docs)

## Endpoints Disponibles

### Controlador de Agentes

- **Obtener todos los agentes:** `GET /agents`
- **Obtener agente por ID:** `GET /agents/{id}`
- **Agregar agente:** `POST /agents/{singerId}`
- **Eliminar agente por ID:** `DELETE /agents/{id}`
- **Actualizar agente por ID:** `PUT /agents/{id}`
- **Obtener cantante asociado a un agente:** `GET /agents/{id}/singer`

### Controlador de Géneros

- **Obtener todos los géneros:** `GET /genres`
- **Obtener género por ID:** `GET /genres/{id}`
- **Agregar género:** `POST /genres`
- **Eliminar género por ID:** `DELETE /genres/{id}`
- **Actualizar género por ID:** `PUT /genres/{id}`
- **Obtener cantantes asociados a un género:** `GET /genres/{id}/singers`

### Controlador de Canciones

- **Obtener todas las canciones:** `GET /songs`
- **Obtener canción por ID:** `GET /songs/{id}`
- **Agregar canción:** `POST /songs/{singerId}`
- **Eliminar canción por ID:** `DELETE /songs/{id}`
- **Actualizar canción por ID:** `PUT /songs/updateSong/{id}`

### Controlador de Cantantes

- **Obtener todos los cantantes:** `GET /singers`
- **Obtener cantante por ID:** `GET /singers/{id}`
- **Agregar cantante:** `POST /singers`
- **Eliminar cantante por ID:** `DELETE /singers/{id}`
- **Actualizar cantante por ID:** `PUT /singers/{id}`
- **Obtener canciones asociadas a un cantante:** `GET /singers/{id}/songs`
- **Obtener géneros asociados a un cantante:** `GET /singers/{id}/genres`
- **Obtener agente asociado a un cantante:** `GET /singers/{id}/agent`

Para acceder a la documentación interactiva de Swagger, visita el enlace proporcionado arriba.
