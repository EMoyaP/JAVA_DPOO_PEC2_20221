package edu.uoc.pac2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
class TextAnalysisTest {

    private final static String TEXT1 = "You may call me heartless, a killer, a monster, a murderer, but I'm still NOTHING compared to the villian that Jay was. This whole contest was a SHAM, an elaborate plot to shame the contestants and feed Jay's massive, massive ego. SURE you think you know him! You've seen him smiling for the cameras, laughing, joking, telling stories, waving his money around like a prop but off camera he was a sinister beast, a cruel CRUEL taskmaster, he treated all of us like slaves, like cattle, like animals! Do you remember Lindsay, she was the first to go, he called her such horrible things that she cried all night, keeping up all up, crying, crying, and more crying, he broke her with his words. I miss my former cast members, all of them very MUCH. And we had to live with him, live in his home, live in his power, deal with his crazy demands. AND FOR WHAT! DID YOU KNOW THAT THE PRIZE ISN'T REAL? He never intended to marry one of us! The carrot on the stick was gone, all that was left was stick, he told us last night that we were all a terrible terrible disappointment and none of us would ever amount to anything, and that regardless of who won the contest he would never speak to any of us again! It's definitely the things like this you can feel in your gut how wrong he is! Well I showed him, he got what he deserved all right, I showed him, I showed him the person I am! I wasn't going to be pushed around any longer, and I wasn't going to let him go on pretending that he was some saint when all he was was a sick sick twisted man who deserved every bit of what he got. The fans need to know, Jay Stacksby is a vile amalgamation of all things evil and bad and the world is a better place without him.";

    private final static String TEXT2 = "Salutations. My name? Myrtle. Myrtle Beech. I am a woman of simple tastes. I enjoy reading, thinking, and doing my taxes. I entered this competition because I want a serious relationship. I want a commitment. The last man I dated was too whimsical. He wanted to go on dates that had no plan. No end goal. Sometimes we would just end up wandering the streets after dinner. He called it a \"walk\". A \"walk\" with no destination. Can you imagine? I like every action I take to have a measurable effect. When I see a movie, I like to walk away with insights that I did not have before. When I take a bike ride, there better be a worthy destination at the end of the bike path. Jay seems frivolous at times. This worries me. However, it is my staunch belief that one does not make and keep money without having a modicum of discipline. As such, I am hopeful. I will now list three things I cannot live without. Water. Emery boards. Dogs. Thank you for the opportunity to introduce myself. I look forward to the competition.";

    private final static String TEXT3 = "Hi, I'm Lily Trebuchet from East Egg, Long Island. I love cats, hiking, and curling up under a warm blanket with a book. So they gave this little questionnaire to use for our bios so lets get started. What are some of my least favorite household chores? Dishes, oh yes it's definitely the dishes, I just hate doing them, don't you? Who is your favorite actor and why? Hmm, that's a hard one, but I think recently I'll have to go with Michael B. Jordan, every bit of that man is handsome, HANDSOME! Do you remember seeing him shirtless? I can't believe what he does for the cameras! Okay okay next question, what is your perfect date? Well it starts with a nice dinner at a delicious but small restaurant, you know like one of those places where the owner is in the back and comes out to talk to you and ask you how your meal was. My favorite form of art? Another hard one, but I think I\'ll have to go with music, music you can feel in your whole body and it is electrifying and best of all, you can dance to it! Okay final question, let's see, What are three things you cannot live without? Well first off, my beautiful, beautiful cat Jerry, he is my heart and spirit animal. Second is pasta, definitely pasta, and the third I think is my family, I love all of them very much and they support me in everything I do. I know Jay Stacksby is a handsome man and all of us want to be the first to walk down the aisle with him, but I think he might truly be the one for me. Okay that's it for the bio, I hope you have fun watching the show!";

    private final static String TEXT4 = "A good day to you all, I am Gregg T Fishy, of the Fishy Enterprise fortune. I am 37 years young. An adventurous spirit and I've never lost my sense of childlike wonder. I do love to be in the backyard gardening and I have the most extraordinary time when I'm fishing. Fishing for what, you might ask? Why, fishing for compliments of course! I have a stunning pair of radiant blue eyes. They will pierce the soul of anyone who dare gaze upon my countenance. I quite enjoy going on long jaunts through garden paths and short walks through greenhouses. I hope that Jay will be as absolutely interesting as he appears on the television. I find that he has some of the most curious tastes in style and humor. When I'm out and about I quite enjoy hearing tales that instill in my heart of hearts the fascination that beguiles my every day life. Every fiber of my being scintillates and vascillates with extreme pleasure during one of these charming anecdotes and significantly pleases my beautiful personage. I cannot wait to enjoy being on A Brand New Jay. It certainly seems like a grand time to explore life and love.";

    private TextAnalysis text;

    @Test
    void testNormalizeSentenceEndSymbols(){
        text = new TextAnalysis("Hello! How are you? I'm fine.");
        assertEquals("Hello. How are you. I'm fine.", text.normalizeSentenceEndSymbols());
        assertEquals("Hello. How are you. I'm fine.", text.getText());

        text = new TextAnalysis("¡Hola! La encapsulación, el testing, la herencia y el polimorfismo son muy importantes!!!!");
        assertEquals("¡Hola. La encapsulación, el testing, la herencia y el polimorfismo son muy importantes....", text.normalizeSentenceEndSymbols());
        assertEquals("¡Hola. La encapsulación, el testing, la herencia y el polimorfismo son muy importantes....", text.getText());

        text = new TextAnalysis("¡!¿?;:...");
        assertEquals("¡.¿......", text.normalizeSentenceEndSymbols());
        assertEquals("¡.¿......", text.getText());
    }

    @Test
    void testGetBagOfWords() {
        text = new TextAnalysis("WE are the best programmers. We are the champions!!!");
        assertArrayEquals(new String[]{"we", "are", "the", "best", "programmers", "we", "are", "the", "champions"},text.getBagOfWords());

        text = new TextAnalysis("Where did you go, friend? We nearly saw each other.");
        assertArrayEquals(new String[]{"where", "did", "you", "go", "friend", "we", "nearly", "saw", "each", "other"},text.getBagOfWords());

        text = new TextAnalysis("Hello?? Bye? Hello! Bye!!!");
        assertArrayEquals(new String[]{"hello", "bye", "hello", "bye"},text.getBagOfWords());

        text = new TextAnalysis("\"Hello??\", said the murderer. Bye? Hello! Bye!!!");
        assertArrayEquals(new String[]{"hello", "said", "the", "murderer", "bye", "hello", "bye"},text.getBagOfWords());
    }

    @Test
    void testGetAverageSentenceLength() {
        text = new TextAnalysis(TEXT1);
        assertEquals(22.066666666666666, text.getAverageSentenceLength(), 0.01);

        text = new TextAnalysis(TEXT2);
        assertEquals(6.75, text.getAverageSentenceLength(), 0.01);

        text = new TextAnalysis(TEXT3);
        assertEquals(15.842105263157896, text.getAverageSentenceLength(), 0.01);

        text = new TextAnalysis(TEXT4);
        assertEquals(13.466666666666667, text.getAverageSentenceLength(), 0.01);
    }



    @Test
    void testIsIncluded(){
        text = new TextAnalysis("bxarlqpccenp");
        assertTrue(text.isIncluded("apple"));

        text = new TextAnalysis("rtuhyydense");
        assertFalse(text.isIncluded("toy"));

        text = new TextAnalysis("toyhus");
        assertFalse(text.isIncluded("toyrus"));

        text = new TextAnalysis("toyhrus");
        assertTrue(text.isIncluded("toyrus"));

        text = new TextAnalysis("rhuystoy");
        assertTrue(text.isIncluded("toyrus"));

        text = new TextAnalysis("ruhylpestoy");
        assertFalse(text.isIncluded("toysrus"));

        text = new TextAnalysis("rhuyabaastosy");
        assertTrue(text.isIncluded("toysrus"));
    }
}
