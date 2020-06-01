-- phpMyAdmin SQL Dump
-- version 4.1.7
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Giu 26, 2019 alle 15:48
-- Versione del server: 5.6.33-log
-- PHP Version: 5.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `my_alessandroamedei18@gmail.com`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `wikiArt`
--

CREATE TABLE IF NOT EXISTS `wikiArt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(999) NOT NULL,
  `autore` varchar(999) NOT NULL,
  `descrizione` varchar(10000) NOT NULL,
  `storia` varchar(10000) NOT NULL,
  `luogo` varchar(999) NOT NULL,
  `anno_creazione` varchar(999) NOT NULL,
  `id_app` varchar(999) NOT NULL,
  `url_video` varchar(999) NOT NULL,
  `url_audio` varchar(999) NOT NULL,
  `url_image` varchar(999) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Dump dei dati per la tabella `wikiArt`
--

INSERT INTO `wikiArt` (`id`, `nome`, `autore`, `descrizione`, `storia`, `luogo`, `anno_creazione`, `id_app`, `url_video`, `url_audio`, `url_image`) VALUES
(1, 'La Gioconda', 'Leonardo Da Vinci', 'La figura si staglia davanti a un vasto paesaggio deserto con il quale costituisce una cosa sola. Questo permette anche di vedere il viso di tre quarti e quindi, oltre che di fronte, anche in parte di lato, penetrandone psicologicamente i diversi aspetti. Il lieve trapasso ed i piani dal buio alla luce, lo sfumato, la leggerissima sfocatura di immagine, esprimono quella palpitazione, che fanno della Gioconda una persona umana nella migliore accezione rinascimentale. Questo spiega anche il sorriso, che esprime il rapporto di amore tra persona e natura.', 'Da Vinci aveva cominciato il quadro nel 1503 e per quattro anni ci mise la mano; poi, mise da parte il dipinto. Si era trasferito a Parigi nel 1516, su invito del re di Francia, e riprese il suo lavoro sulla Gioconda. Ci sono voluti altri tre anni per completarlo. Ci sono voluti altri 300 anni prima che qualcuno, al di fuori di Italia, lo considerasse, annoverandolo tra i capolavori del Rinascimento.', 'Louvre, Parigi, Francia', '1503-04', 'MonnaLisa', 'http://ame97software.altervista.org/wikiArt/video/La_Gioconda_-_Leonardo_Da_Vinci.mp4', 'http://ppm19vignoli.altervista.org/Wiki_Art_Unifi/Audio/Audio_Gioconda.mp3', 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/Mona_Lisa%2C_by_Leonardo_da_Vinci%2C_from_C2RMF_retouched.jpg/687px-Mona_Lisa%2C_by_Leonardo_da_Vinci%2C_from_C2RMF_retouched.jpg'),
(18, 'Levar del Sole', 'Claude Monet', 'Il quadro rappresenta il porto di Le Havre, sul canale della Manica, alle prime luci del mattino, proprio come vuole indicare il titolo stesso. \nIn primo piano possiamo osservare al centro una piccola barca di ritorno dalla pesca notturna, ed una in fondo a sinistra. Sullo sfondo sagome sfocate di navi grandi, abbozzate come ombre scure. Il protagonista si rivela il sole, che si riflette in parte in acqua, strano e dipinto in modo errato, in quanto luminoso quanto il cielo circostante e dunque irreale. Questo elemento imprime carattere al dipinto, donando un che di fantasioso e che ci porta in un mondo differente, quasi immaginato e surreale.', 'Nel 1872, Monet, a Le Havre, volle ritrarne il porto in sei tele da realizzarsi al mattino, col giorno, col crepuscolo, e di notte, oltre che da vari punti di vista: alcune, infatti, le fece dal livello del mare ed altre dalla stanza di un albergo che sovrasta le infrastrutture portuali.', 'Museo Marmottan Monet, Parigi, Francia', '1872', 'levar_sole', 'http://ppm19vignoli.altervista.org/Wiki_Art_Unifi/Video/Claude_Monet_Impression_soleil_levant_IAM_Contemporary_Art_Video.mp4', 'http://ppm19vignoli.altervista.org/Wiki_Art_Unifi/Audio/Audio_Monet.mp3', 'http://www.arte.it/foto/600x450/7e/24750-Monet_-_Impressione_levar_del_sole_1872.jpg'),
(20, 'Il Bacio', 'Gustav Klimt', 'Un uomo e una donna si abbracciano al centro di uno spazio astratto. Lui avvolge il viso della donna con le sue mani teneramente e si china sul volto di lei da sopra. La giovane ha il viso reclinato di lato e poggiato sulla sua spalla sinistra. Il suo braccio destro sollevato e la mano poggia sul collo di lui. Il braccio sinistro della donna, invece, flesso contro la sua spalla. La mano stringe quella di lui. Il volto della donna chiaro e arrossato leggermente sulle gote. Gli occhi sono chiusi e la sua espressione serena ed estatica. Tra i capelli vi sono alcuni fiori che decorano la capigliatura.', 'Gustav Klimt (1862-1918), pittore austriaco della Secessione viennese, movimento artistico fondato dallo stesso Klimt che aveva lâ€™obiettivo di portare lâ€™arte al di fuori dei confini della tradizione dellâ€™Accademia di Belle Arti, per arrivare a una fusione completa delle varie arti.', 'Osterreichische Galerie Belvedere, Vienna, Austria', '1907-08', 'il_bacio', 'http://ppm19vignoli.altervista.org/Wiki_Art_Unifi/Video/Gustav_Klimt_-_Il_bacio.mp4', 'http://ppm19vignoli.altervista.org/Wiki_Art_Unifi/Audio/Audio_Klimt.mp3', 'https://cultura.biografieonline.it/wp-content/uploads/2012/07/il-bacio-di-klimt.jpg'),
(21, 'Campo di grano', 'Vincent Van Gogh', 'Su un campo di grano ormai maturo vola uno stormo di corvi neri. Dal primo piano una stradina fende gli steli dorati e si perde nel campo in lontananza. I bordi del sentiero sono popolati da erba verde. A destra invece, il campo confina con il terreno argilloso. Nel cielo scuro vorticano nubi nere che si confondono con il volo dei corvi.', 'Vincent van Gogh dipinse Campo di grano con corvi nelle campagne di Auvers-sur-Oise nel 1890. A luglio dello stesso anno con la pistola si esplose un colpo allâ€™addome e scomparve dopo qualche giorno a 37 anni.', 'Van Gogh Museum, Amsterdam, Paesi Passi', '1890', 'campo_di_grano', 'http://ppm19vignoli.altervista.org/Wiki_Art_Unifi/Video/Van_Gogh_Campo_di_grano_con_volo_di_corvi.mp4', 'http://ppm19vignoli.altervista.org/Wiki_Art_Unifi/Audio/Audio_VVG.mp3', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Vincent_van_Gogh_-_Wheatfield_with_crows_-_Google_Art_Project.jpg/600px-Vincent_van_Gogh_-_Wheatfield_with_crows_-_Google_Art_Project.jpg'),
(25, 'Statua del Burkina', 'Sconosciuto', 'La stauta del Burkina fu trovata nel 1956 nel Polo Nord. Chi cazzo la ha portata li?', 'La stauta del Burkina fu trovata nel 1956 nel Polo Nord. Chi cazzo la ha portata li?', 'Polo Nord', 'Circa 1900', 'bURKINA', 'http://ppm19vignoli.altervista.org/Wiki_Art_Unifi/Video/Parigi_La_Cattedrale_Di_Notre-Dame.mp4', 'http://ppm19vignoli.altervista.org/Wiki_Art_Unifi/Audio/Audio_ND.mp3', 'http://ame97software.altervista.org/wikiArt/images/Statua-Africana-In-Bronzo-Maternit__-Madre-E-Bambino.jpg'),
(24, 'Notre-Dame', 'Maurice de Sully', 'Notre Dame di Parigi risulta essere una cattedrale molto precoce, a forma di rettangolo aureo, che rivela infatti molti elementi orizzontali, a discapito della ricerca di leggerezza. Sono presenti logge con statue, cornici, gallerie e torrioni massicci che alludono al Romanico. Nonostante compaiano anche bifore slanciate, i portali non sono eccessivamente strombati e sono scarsi i giochi chiaroscurali. Nonostante la pianta basilicale, la parte interna si rivela quasi centralizzata: il transetto incrocia il corpo longitudinale delle navate quasi al centro del loro sviluppo ed inoltre si trova una doppia successione di navate laterali che circondano il vasto spazio interno.', 'Realizzata nello stile architettonico del primo periodo gotico, Notre Dame di Parigi si rivela una cattedrale europea molto antica, visitata ogni anno da milioni di turisti e pellegrini provenienti da ogni parte del mondo. Nel tardo pomeriggio del 15 aprile 2019, un incendio la ha danneggiata e si trova, al momento, in una fase di ristrutturazione.', 'Parigi, Francia', '1344', 'Notre-Dame', 'http://ppm19vignoli.altervista.org/Wiki_Art_Unifi/Video/Parigi_La_Cattedrale_Di_Notre-Dame.mp4', 'http://ppm19vignoli.altervista.org/Wiki_Art_Unifi/Audio/Audio_ND.mp3', 'https://www.parigi.it/images/3-notredameparis-c15517.jpg?v=e525');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
