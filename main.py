# import os
# os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

# import io
# import tensorflow as tf
# from tensorflow import keras
# import numpy as np
# from PIL import Image

# from flask import Flask, request, jsonify

# model = keras.models.load_model("model.h5")
# label = {0: 'Battery', 1: 'Biological', 2: 'Cardboard', 3: 'Clothes', 4: 'Glass', 5: 'Metal', 6: 'Paper', 7: 'Shoes'}

# app = Flask(__name__)

# IMG_SIZE = (400, 400)


# def predict_label(img):
#     """Memproses dan memprediksi label untuk sebuah gambar."""
    
#     # Mengonversi PIL Image menjadi array NumPy
#     img = np.array(img)

#     # Memeriksa dimensi gambar
#     if img.shape[0] != 400 or img.shape[1] != 400:
#         img = Image.fromarray(img)
#         img = img.resize((400, 400))  # Gunakan Pillow untuk meresize
#         img = np.array(img)

#     # Mengubah bentuk sesuai yang diinginkan
#     img = img.reshape((1, 400, 400, 3))

#     pred = model.predict(img)
#     result = label[np.argmax(pred)]
#     confidence = np.max(pred)  # Mendapatkan skor kepercayaan
#     return result, confidence
    


# @app.route("/predict", methods=["POST"])
# def index():
#     """Handles image prediction requests."""
#     file = request.files.get('file')
#     if file is None or file.filename == "":
#         return jsonify({"error": "no file"})

#     image_bytes = file.read()
#     img = Image.open(io.BytesIO(image_bytes))
#     predicted_class, confidence = predict_label(img)

#     response_message = f"Predicted class: '{predicted_class}' with confidence: {confidence:.2%}."
#     return jsonify({"prediction": response_message})

# if __name__ == "__main__":
#     app.run(debug=True)

import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

import io
import tensorflow as tf
from tensorflow import keras
import numpy as np
from PIL import Image

from flask import Flask, request, jsonify

model = keras.models.load_model("model_garbage.h5")
label = {0: 'Battery', 1: 'Makanan', 2: 'Kardus', 3: 'Pakaian', 4: 'Kaca', 5: 'Logam', 6: 'Kertas', 7: 'Plastik', 8:'Sepatu'}

app = Flask(__name__)

IMG_SIZE = (400, 400)


def predict_label(img):
    """Memproses dan memprediksi label untuk sebuah gambar."""
    
    # Mengonversi PIL Image menjadi array NumPy
    img = np.array(img)

    # Memeriksa dimensi gambar
    if img.shape[0] != 400 or img.shape[1] != 400:
        img = Image.fromarray(img)
        img = img.resize((400, 400))  # Gunakan Pillow untuk meresize
        img = np.array(img)

    # Mengubah bentuk sesuai yang diinginkan
    img = img.reshape((1, 400, 400, 3))

    pred = model.predict(img)
    predicted_class_index = np.argmax(pred)
    result = label[predicted_class_index]
    confidence = np.max(pred)  # Mendapatkan skor kepercayaan

    # Menentukan kategori terurai atau tidak terurai berdasarkan label
    if predicted_class_index in [1, 2, 6]:  # Biological or Paper
        category = "Bisa Terurai"
    else:
        category = "Tidak Bisa Terurai"

    response_message = f"Jenis barang yang anda foto adalah: '{result}' dengan presentase : {confidence:.2%}. Kategori sampah yang : {category}."
    return result, confidence, category


@app.route("/predict", methods=["POST"])
def index():
    """Handles image prediction requests."""
    file = request.files.get('file')
    if file is None or file.filename == "":
        return jsonify({"error": "no file"})

    image_bytes = file.read()
    img = Image.open(io.BytesIO(image_bytes))
    predicted_class, confidence, category = predict_label(img)

    response_message = f"Jenis barang yang anda foto adalah: '{predicted_class}' dengan presentase : {confidence:.2%}. Kategori sampah yang : {category}."
    return jsonify({"prediction": response_message})


if __name__ == "__main__":
    app.run(debug=True)
