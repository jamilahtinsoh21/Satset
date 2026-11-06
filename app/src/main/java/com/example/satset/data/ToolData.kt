package com.example.satset.data

object ToolData {
    val all = listOf(
        AiTool(
            1, "Gemini", "Asisten", listOf("Nulis"),
            "Ringkas materi dan bantu tugas harian",
            "Asisten AI dari Google yang terhubung dengan layanan Google seperti Docs, Drive, dan Gmail. Cocok untuk meringkas materi dan membantu tugas sehari-hari.",
            listOf("Ringkas materi", "Nulis di Docs", "Tanya jawab cepat"),
            listOf("Nyambung ke layanan Google", "Respons cepat"),
            "Model terbaik dibatasi per hari",
            "https://gemini.google.com", "#4285F4", 4.5, 120
        ),
        AiTool(
            2, "Perplexity", "Riset", listOf("Riset"),
            "Cari referensi lengkap dengan sumber",
            "Mesin pencari berbasis AI yang menjawab pertanyaan lengkap dengan tautan sumbernya, cocok untuk mencari referensi tugas.",
            listOf("Cari referensi", "Cek fakta", "Riset topik"),
            listOf("Jawaban disertai sumber", "Cocok untuk riset"),
            "Pencarian mendalam dibatasi per hari",
            "https://www.perplexity.ai", "#20808D", 4.6, 95
        ),
        AiTool(
            3, "Claude", "Asisten", listOf("Nulis"),
            "Rapikan tulisan dan jelasin konsep",
            "Asisten AI dari Anthropic yang kuat untuk menulis, merapikan tulisan, dan menjelaskan konsep dengan bahasa yang mudah dipahami.",
            listOf("Rapikan tulisan", "Jelasin konsep", "Analisis dokumen"),
            listOf("Gaya bahasa natural", "Bisa membaca dokumen panjang"),
            "Pemakaian harian dibatasi di versi gratis",
            "https://claude.ai", "#D97757", 4.7, 110
        ),
        AiTool(
            4, "NotebookLM", "Belajar", listOf("Belajar"),
            "Ubah catatan jadi ringkasan dan kuis",
            "Alat dari Google untuk belajar dari materi milikmu sendiri. Unggah catatan atau PDF, lalu minta ringkasan, tanya jawab, dan kuis.",
            listOf("Ringkas catatan", "Buat kuis", "Belajar dari PDF"),
            listOf("Jawaban berdasarkan materimu", "Cocok untuk persiapan ujian"),
            "Jumlah sumber dan fitur dibatasi di versi gratis",
            "https://notebooklm.google.com", "#5F6368", 4.4, 70
        ),
        AiTool(
            5, "ChatGPT", "Asisten", listOf("Nulis", "Belajar"),
            "Tanya jawab dan bantu tugas harian",
            "Asisten AI serbaguna dari OpenAI untuk tanya jawab, brainstorming ide, dan membantu mengerjakan tugas harian.",
            listOf("Tanya jawab", "Brainstorming", "Bantu menulis"),
            listOf("Serbaguna", "Mudah dipakai"),
            "Model terbaik dibatasi per jam atau per hari",
            "https://chatgpt.com", "#10A37F", 4.6, 150
        ),
        AiTool(
            6, "Canva", "Desain", listOf("Desain"),
            "Bikin poster dan presentasi dengan cepat",
            "Platform desain dengan banyak template untuk membuat poster, presentasi, dan konten media sosial tanpa perlu keahlian desain.",
            listOf("Poster", "Presentasi", "Konten sosmed"),
            listOf("Template siap pakai", "Mudah untuk pemula"),
            "Sebagian template dan fitur hanya untuk versi berbayar",
            "https://www.canva.com", "#00A3AD", 4.5, 85
        )
    )
}