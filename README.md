# 📊 Control de Notas

Aplicación Android desarrollada en **Java** con **Android Studio** que calcula la nota definitiva de un número indeterminado de estudiantes e indica cuántos perdieron la materia.

## 📋 Enunciado

Implementar una aplicación que permita calcular la nota definitiva de "n" estudiantes, sabiendo que:

| Nota | Porcentaje |
|------|-----------|
| Nota 1 | 20 % |
| Nota 2 | 30 % |
| Nota 3 | 15 % |
| Nota 4 | 35 % |

Cada nota debe estar entre **1 y 5**. El programa debe indicar cuántos estudiantes perdieron la materia (nota definitiva menor a **3.0**).

## ✨ Funcionalidades

- Ingreso del nombre y las cuatro notas de cada estudiante.
- Validación de que cada nota esté en el rango de 1 a 5.
- Cálculo de la nota definitiva multiplicando cada nota por su porcentaje y acumulando los resultados.
- Resultado en pantalla o como mensaje emergente (Toast), según la opción elegida.
- Limpieza automática de los campos después de cada cálculo (opcional).
- Resumen del grupo con el total de estudiantes, cuántos aprobaron y cuántos perdieron.
- Reinicio de los contadores para registrar un nuevo grupo.

## 🧩 Componentes de la interfaz

| Componente | Uso |
|-----------|-----|
| `TextView` | Título, etiquetas y resultados |
| `EditText` | Nombre del estudiante y las cuatro notas |
| `RadioButton` | Elegir si el resultado se muestra en pantalla o como mensaje |
| `CheckBox` | Limpiar los campos después de calcular |
| `Button` | Calcular, Finalizar grupo y Reiniciar |

## 🧮 Fórmula

```
Definitiva = (Nota1 × 0.20) + (Nota2 × 0.30) + (Nota3 × 0.15) + (Nota4 × 0.35)
```

- Definitiva **≥ 3.0** → Aprobó
- Definitiva **< 3.0** → Perdió

## 🧪 Ejemplos de prueba

| Estudiante | N1 | N2 | N3 | N4 | Definitiva | Resultado |
|-----------|----|----|----|----|-----------|-----------|
| Ana | 4.0 | 3.5 | 4.5 | 4.0 | 3.93 | Aprobó |
| Luis | 2.0 | 2.5 | 3.0 | 2.0 | 2.30 | Perdió |
| Sara | 3.0 | 3.0 | 3.0 | 3.0 | 3.00 | Aprobó |

## 📁 Estructura principal

```
app/src/main/
├── java/com/example/controlnotas/
│   └── MainActivity.java      # Lógica: validación, cálculo y conteo
└── res/layout/
    └── activity_main.xml      # Diseño de la interfaz
```

## 🛠️ Tecnologías

- Android Studio
- Java
- SDK mínimo: API 24 (Android 7.0)
- Gradle con Kotlin DSL

## ▶️ Cómo ejecutar

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/USUARIO/ControlNotas.git
   ```
2. Abrir la carpeta del proyecto en Android Studio.
3. Esperar a que Gradle termine de sincronizar.
4. Seleccionar un emulador o dispositivo y pulsar **Run ▶**.

## 👥 Integrantes

- Laura Camila García López
- Juan David García Vargas
- Jhoan Sebastian Villarreal Rueda