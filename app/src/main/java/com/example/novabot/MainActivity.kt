package com.example.novabot

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etMessage: EditText
    private lateinit var btnSend: Button
    private lateinit var chatContainer: LinearLayout
    private lateinit var scrollView: ScrollView

    // --------------------------------
    // CONTEXT VARIABLES
    // --------------------------------

    private var userName = ""
    private var currentTopic = ""

    // --------------------------------
    // ACTIVITY CREATED
    // --------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        etMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)
        chatContainer = findViewById(R.id.chatContainer)
        scrollView = findViewById(R.id.scrollView)

        showWelcomeMessage()

        btnSend.setOnClickListener {

            sendMessage()

        }

        etMessage.setOnEditorActionListener { _, _, _ ->

            sendMessage()

            true
        }
    }

    // --------------------------------
    // WELCOME MESSAGE
    // --------------------------------

    private fun showWelcomeMessage() {

        addBotMessage(
            "Hello! 👋\n\n" +
                    "I'm NovaBot, your simple AI chatbot.\n\n" +
                    "I can remember the topic of our conversation " +
                    "and answer follow-up questions.\n\n" +
                    "What's your name?"
        )
    }

    // --------------------------------
    // SEND MESSAGE
    // --------------------------------

    private fun sendMessage() {

        val message = etMessage.text.toString().trim()

        if (message.isEmpty()) {

            return
        }

        addUserMessage(message)

        etMessage.text.clear()

        val response = generateResponse(message)

        addBotMessage(response)
    }

    // --------------------------------
    // CHATBOT BRAIN
    // --------------------------------

    private fun generateResponse(message: String): String {

        val text = message.lowercase().trim()

        // --------------------------------
        // FIRST TURN - GET NAME
        // --------------------------------

        if (userName.isEmpty()) {

            userName = message.trim()

            return "Nice to meet you, $userName! 😊\n\n" +
                    "What would you like to learn about?\n\n" +
                    "You can type:\n" +
                    "• Artificial Intelligence\n" +
                    "• Machine Learning\n" +
                    "• Android Development\n" +
                    "• Python"
        }

        // --------------------------------
        // SECOND TURN - GET TOPIC
        // --------------------------------

        if (currentTopic.isEmpty()) {

            when {

                text.contains("artificial intelligence") ||
                        text == "ai" -> {

                    currentTopic = "Artificial Intelligence"

                    return "Excellent choice, $userName! 🤖\n\n" +
                            "Artificial Intelligence (AI) is a field " +
                            "of computer science that develops systems " +
                            "capable of performing tasks that normally " +
                            "require human intelligence.\n\n" +
                            "You can now ask follow-up questions about AI."
                }

                text.contains("machine learning") ||
                        text == "ml" -> {

                    currentTopic = "Machine Learning"

                    return "Excellent choice, $userName! 📚\n\n" +
                            "Machine Learning is a branch of AI where " +
                            "computers learn patterns from data and use " +
                            "those patterns to make predictions or decisions.\n\n" +
                            "You can now ask follow-up questions about Machine Learning."
                }

                text.contains("android") -> {

                    currentTopic = "Android Development"

                    return "Great choice, $userName! 📱\n\n" +
                            "Android development is the process of " +
                            "creating applications for Android devices.\n\n" +
                            "You can now ask follow-up questions about Android."
                }

                text.contains("python") -> {

                    currentTopic = "Python"

                    return "Good choice, $userName! 🐍\n\n" +
                            "Python is a popular programming language " +
                            "used in AI, Machine Learning, Data Science, " +
                            "web development and automation.\n\n" +
                            "You can now ask follow-up questions about Python."
                }

                else -> {

                    currentTopic = message

                    return "Okay, $userName! 👍\n\n" +
                            "I'll remember that we're discussing " +
                            "\"$currentTopic\".\n\n" +
                            "You can now ask me a follow-up question."
                }
            }
        }

        // --------------------------------
        // FOLLOW-UP: APPLICATIONS
        // --------------------------------

        if (text.contains("application") ||
            text.contains("applications") ||
            text.contains("uses") ||
            text.contains("used")) {

            return getApplicationsResponse()
        }

        // --------------------------------
        // FOLLOW-UP: ADVANTAGES
        // --------------------------------

        if (text.contains("advantage") ||
            text.contains("advantages") ||
            text.contains("benefit") ||
            text.contains("benefits")) {

            return getAdvantagesResponse()
        }

        // --------------------------------
        // FOLLOW-UP: DISADVANTAGES
        // --------------------------------

        if (text.contains("disadvantage") ||
            text.contains("disadvantages") ||
            text.contains("limitation") ||
            text.contains("limitations")) {

            return getDisadvantagesResponse()
        }

        // --------------------------------
        // FOLLOW-UP: EXAMPLES
        // --------------------------------

        if (text.contains("example") ||
            text.contains("examples")) {

            return getExamplesResponse()
        }

        // --------------------------------
        // FOLLOW-UP: DEFINITION
        // --------------------------------

        if (text.contains("definition") ||
            text.contains("define") ||
            text.contains("what is")) {

            return getDefinitionResponse()
        }

        // --------------------------------
        // GREETING
        // --------------------------------

        if (text.contains("hello") ||
            text.contains("hi") ||
            text.contains("hey")) {

            return "Hello again, $userName! 👋\n\n" +
                    "We're still discussing $currentTopic.\n\n" +
                    "What would you like to know next?"
        }

        // --------------------------------
        // THANK YOU
        // --------------------------------

        if (text.contains("thank")) {

            return "You're welcome, $userName! 😊\n\n" +
                    "You can continue asking questions about " +
                    "$currentTopic."
        }

        // --------------------------------
        // GOODBYE
        // --------------------------------

        if (text.contains("bye") ||
            text.contains("goodbye")) {

            return "Goodbye, $userName! 👋\n\n" +
                    "It was nice talking with you about " +
                    "$currentTopic."
        }

        // --------------------------------
        // GENERIC CONTEXTUAL RESPONSE
        // --------------------------------

        return "I understand your question, $userName. 👍\n\n" +
                "We're currently discussing \"$currentTopic\".\n\n" +
                "Try asking:\n" +
                "• What is it?\n" +
                "• What are its applications?\n" +
                "• What are its advantages?\n" +
                "• What are its disadvantages?\n" +
                "• Give me examples."
    }

    // ============================================
    // DEFINITION RESPONSE
    // ============================================

    private fun getDefinitionResponse(): String {

        return when (currentTopic) {

            "Artificial Intelligence" ->

                "Since we're discussing Artificial Intelligence:\n\n" +
                        "AI is technology that enables computers " +
                        "to perform tasks that normally require " +
                        "human intelligence, such as learning, " +
                        "reasoning and problem solving."

            "Machine Learning" ->

                "Since we're discussing Machine Learning:\n\n" +
                        "Machine Learning is a technique where " +
                        "computers learn patterns from data and " +
                        "use those patterns to make predictions."

            "Android Development" ->

                "Since we're discussing Android Development:\n\n" +
                        "Android development is the process of designing " +
                        "and building applications for Android smartphones, " +
                        "tablets and other Android devices."

            "Python" ->

                "Since we're discussing Python:\n\n" +
                        "Python is a high-level programming language " +
                        "known for its readable syntax and extensive libraries."

            else ->

                "We are currently discussing $currentTopic."
        }
    }

    // ============================================
    // APPLICATIONS RESPONSE
    // ============================================

    private fun getApplicationsResponse(): String {

        return when (currentTopic) {

            "Artificial Intelligence" ->

                "Because our current topic is Artificial Intelligence, " +
                        "its applications include:\n\n" +
                        "🏥 Healthcare\n" +
                        "🎓 Education\n" +
                        "🚗 Autonomous vehicles\n" +
                        "🤖 Robotics\n" +
                        "💬 Chatbots\n" +
                        "🔐 Cybersecurity\n" +
                        "🛒 Recommendation systems"

            "Machine Learning" ->

                "Applications of Machine Learning include:\n\n" +
                        "• Disease prediction\n" +
                        "• Fraud detection\n" +
                        "• Recommendation systems\n" +
                        "• Image recognition\n" +
                        "• Speech recognition\n" +
                        "• Predictive analysis"

            "Android Development" ->

                "Android applications can be used for:\n\n" +
                        "• Education\n" +
                        "• Banking\n" +
                        "• Healthcare\n" +
                        "• Entertainment\n" +
                        "• Communication\n" +
                        "• E-commerce"

            "Python" ->

                "Python is commonly used for:\n\n" +
                        "• Artificial Intelligence\n" +
                        "• Machine Learning\n" +
                        "• Data Science\n" +
                        "• Web Development\n" +
                        "• Automation\n" +
                        "• Scientific Computing"

            else ->

                "Applications depend on the topic \"$currentTopic\"."
        }
    }

    // ============================================
    // ADVANTAGES RESPONSE
    // ============================================

    private fun getAdvantagesResponse(): String {

        return when (currentTopic) {

            "Artificial Intelligence" ->

                "Advantages of AI include:\n\n" +
                        "✓ Automation of repetitive tasks\n" +
                        "✓ Fast data analysis\n" +
                        "✓ Pattern recognition\n" +
                        "✓ Decision support\n" +
                        "✓ 24/7 operation"

            "Machine Learning" ->

                "Advantages of Machine Learning include:\n\n" +
                        "✓ Learns from data\n" +
                        "✓ Identifies complex patterns\n" +
                        "✓ Supports prediction\n" +
                        "✓ Can automate decisions"

            "Android Development" ->

                "Advantages of Android development include:\n\n" +
                        "✓ Large user base\n" +
                        "✓ Flexible development\n" +
                        "✓ Wide range of devices\n" +
                        "✓ Many development tools"

            "Python" ->

                "Advantages of Python include:\n\n" +
                        "✓ Simple syntax\n" +
                        "✓ Easy to learn\n" +
                        "✓ Large library ecosystem\n" +
                        "✓ Useful for AI and Data Science"

            else ->

                "Advantages depend on \"$currentTopic\"."
        }
    }

    // ============================================
    // DISADVANTAGES RESPONSE
    // ============================================

    private fun getDisadvantagesResponse(): String {

        return when (currentTopic) {

            "Artificial Intelligence" ->

                "Some limitations of AI include:\n\n" +
                        "• Development cost\n" +
                        "• Dependence on quality data\n" +
                        "• Ethical concerns\n" +
                        "• Privacy concerns\n" +
                        "• Some systems can be difficult to explain"

            "Machine Learning" ->

                "Some limitations of Machine Learning include:\n\n" +
                        "• Requires suitable data\n" +
                        "• Training can require significant resources\n" +
                        "• Poor data can produce poor results\n" +
                        "• Models may be difficult to interpret"

            "Android Development" ->

                "Some challenges include:\n\n" +
                        "• Device fragmentation\n" +
                        "• Different screen sizes\n" +
                        "• Different Android versions\n" +
                        "• Testing requirements"

            "Python" ->

                "Some limitations of Python include:\n\n" +
                        "• Can be slower than some compiled languages\n" +
                        "• Higher memory usage in some applications\n" +
                        "• Not always ideal for mobile development"

            else ->

                "Limitations depend on \"$currentTopic\"."
        }
    }

    // ============================================
    // EXAMPLES RESPONSE
    // ============================================

    private fun getExamplesResponse(): String {

        return when (currentTopic) {

            "Artificial Intelligence" ->

                "Examples of AI include:\n\n" +
                        "🤖 Virtual assistants\n" +
                        "🚗 Driver-assistance systems\n" +
                        "🏥 Medical decision-support systems\n" +
                        "💬 Chatbots\n" +
                        "🎬 Recommendation systems"

            "Machine Learning" ->

                "Examples of Machine Learning include:\n\n" +
                        "• Spam email detection\n" +
                        "• Movie recommendations\n" +
                        "• Image classification\n" +
                        "• House-price prediction"

            "Android Development" ->

                "Examples of Android applications include:\n\n" +
                        "• Chat applications\n" +
                        "• Shopping applications\n" +
                        "• Learning applications\n" +
                        "• Fitness applications"

            "Python" ->

                "Examples of Python projects include:\n\n" +
                        "• AI applications\n" +
                        "• Data-analysis programs\n" +
                        "• Web applications\n" +
                        "• Automation tools"

            else ->

                "An example can be created for \"$currentTopic\"."
        }
    }

    // ============================================
    // DISPLAY USER MESSAGE
    // ============================================

    private fun addUserMessage(message: String) {

        val messageView = TextView(this)

        messageView.text =
            "You\n$message"

        messageView.textSize = 16f

        messageView.setTextColor(
            Color.parseColor("#202124")
        )

        messageView.setPadding(
            18,
            12,
            18,
            12
        )

        messageView.background =
            getDrawable(
                R.drawable.user_message_background
            )

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        params.gravity = Gravity.END

        params.setMargins(
            50,
            8,
            4,
            8
        )

        messageView.layoutParams = params

        chatContainer.addView(messageView)

        scrollToBottom()
    }

    // ============================================
    // DISPLAY BOT MESSAGE
    // ============================================

    private fun addBotMessage(message: String) {

        val messageView = TextView(this)

        messageView.text =
            "NovaBot 🤖\n$message"

        messageView.textSize = 16f

        messageView.setTextColor(
            Color.parseColor("#202124")
        )

        messageView.setPadding(
            18,
            12,
            18,
            12
        )

        messageView.background =
            getDrawable(
                R.drawable.bot_message_background
            )

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        params.gravity = Gravity.START

        params.setMargins(
            4,
            8,
            50,
            8
        )

        messageView.layoutParams = params

        chatContainer.addView(messageView)

        scrollToBottom()
    }

    // ============================================
    // SCROLL TO LATEST MESSAGE
    // ============================================

    private fun scrollToBottom() {

        scrollView.post {

            scrollView.fullScroll(
                View.FOCUS_DOWN
            )
        }
    }
}