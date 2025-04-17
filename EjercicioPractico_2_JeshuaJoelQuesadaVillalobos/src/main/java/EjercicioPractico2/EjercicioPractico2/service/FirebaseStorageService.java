/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package EjercicioPractico2.EjercicioPractico2.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//se utilizara el del portafolio para poder realizar mas rapido el examen y simplificar el proceso de resolución de este mismo
@Service
public interface FirebaseStorageService {

    public String cargaImagen(MultipartFile archivoLocalCliente, String carpeta, Long id);

    // El BucketName es el <id_del_proyecto> + ".appspot.com"
    final String BucketName = "techshop-70262.appspot.com";

    // Esta es la ruta básica de este proyecto Techshop
    final String rutaSuperiorStorage = "techshop";

    // Ubicación donde se encuentra el archivo de configuración Json
    final String rutaJsonFile = "firebase";

    // El nombre del archivo Json
    final String archivoJsonFile = "techshop-f5b40-firebase-adminsdk-fbsvc-c3c3b528be";
}