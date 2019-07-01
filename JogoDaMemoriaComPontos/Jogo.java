
import java.util.HashMap;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Jogo extends Application {
    public static final int CARD_WIDTH = 70;
    public static final int CARD_HEIGHT = 60;

    private ControleDeJogadas cJog;
    private static Map<String, Image> imagens;
    private Carta cartaCorrente, ultimaCartaAberta;
    private Carta primCartaComp, segCartaComp;


    public static void main(String[] args) {
        launch(args);
    }

    public static Image getImage(String name) {
        return imagens.get(name);
    }

    private static void loadImagens() {
        imagens = new HashMap<>();

        // Armazena a imagem das costas das cartas
        Image aux = new Image("\\Imagens\\back.jpg");
        imagens.put("back", aux);

        // Armazena a imagem da carta vazia
        imagens.put("empty", null);

        // Armazena as demais imagens
        for (int i = 1; i <= ControleDeJogadas.NUMPARES; i++) {
            aux = new Image("\\Imagens\\img" + i + ".jpg");
            imagens.put("img" + i, aux);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        // Carrega imagens
        loadImagens();

        // Cria o controle do jogo
        cJog = new ControleDeJogadas(1);
        ultimaCartaAberta = null;
        cartaCorrente = null;

        // Configura a interface com o usuario
        primaryStage.setTitle("Jogo da memoria");
        // Configurar Labels pontos
        TextField userCountText = new TextField();
        Label userCountLabel = new Label("Pontos Jogador :");
        userCountText.setDisable(true);
        userCountText.setMaxWidth(129);

        TextField computerCountText = new TextField();
        Label computerCountLabel = new Label("Pontos Computador :");
        computerCountText.setDisable(true);
        computerCountText.setMaxWidth(129);
        // Muda dificuldade do computador do jogo
        Label radioGroupLabel = new Label("Dificuldade \nComputador: ");
        ToggleGroup groupRadio = new ToggleGroup();
        RadioButton radEasy = new RadioButton("Easy");
        radEasy.setToggleGroup(groupRadio);
        RadioButton radNormal = new RadioButton("Normal");
        radNormal.setToggleGroup(groupRadio);
        radNormal.setSelected(true);
        RadioButton radHard = new RadioButton("Hard");
        radHard.setToggleGroup(groupRadio);

        // Restart app
        Button btRestart = new Button("New Game");

        // Muda modo de jogo
        Label radioTypeButton = new Label("Modo Jogo: ");
        ToggleGroup groupTypeButton = new ToggleGroup();
        RadioButton radBotaoCarta = new RadioButton("Normal Card");
        radBotaoCarta.setToggleGroup(groupTypeButton);
        radBotaoCarta.setSelected(true);
        RadioButton radBotaoBonus = new RadioButton("Bonus Card");
        radBotaoBonus.setToggleGroup(groupTypeButton);
        RadioButton radBotaoEspecial = new RadioButton("Special Card");
        radBotaoEspecial.setToggleGroup(groupTypeButton);
        //

        // Configurar grid cards
        GridPane tab = new GridPane();
        tab.setAlignment(Pos.CENTER);
        tab.setHgap(10);
        tab.setVgap(10);
        tab.setPadding(new Insets(25, 25, 25, 25));
//        tab.setGridLinesVisible(true);
        tab.addRow(6, btRestart);
        tab.addRow(8, userCountLabel, userCountText);
        tab.addRow(10, computerCountLabel, computerCountText);
        tab.addRow(12, radioGroupLabel);
        tab.addRow(14, radEasy);
        tab.addRow(15, radNormal);
        tab.addRow(16, radHard);
        tab.addRow(17, radioTypeButton);
        tab.addRow(18, radBotaoCarta);
        tab.addRow(19, radBotaoBonus);
        tab.addRow(20, radBotaoEspecial);

        // Verifica o tipo de dificuldade por eventos
        radEasy.setOnAction(e -> {
            if (radEasy.isSelected()) {
//                TODO: MUITOS ELEMENTOS NA LISTA DA INDEX OUTOFBOUNDS
//                cJog.clearList();
                cJog.setMemoriaComputador(2);
            }
        });
        radNormal.setOnAction(e -> {
            if (radNormal.isSelected()) {
                cJog.setMemoriaComputador(1);
            }
        });
        radHard.setOnAction(e -> {
            if (radHard.isSelected()) {
                cJog.setMemoriaComputador(3);
            }
        });

        // Verifica o modo de jogo selecionado
        radBotaoCarta.setOnAction(e -> {
            if (radBotaoCarta.isSelected()) {
                primaryStage.close();
                Platform.runLater(() -> {
                    cJog.setTipoBotao(1);
                });
            }
        });
        radBotaoBonus.setOnAction(e -> {
            if (radBotaoBonus.isSelected()) {
                primaryStage.close();
                Platform.runLater(() -> {
                    cJog.setTipoBotao(2);
                });
            }
        });
//        radBotaoEspecial.setOnAction(e -> {
//            if (radBotaoEspecial.isSelected()) {
//                cJog.setTipoBotao(3);
//            }
//        });


        // Cria os botoes das cartas
        for (int lin = 0; lin < ControleDeJogadas.NLIN; lin++) {
            for (int col = 0; col < ControleDeJogadas.NCOL; col++) {
                Carta bt = cJog.getCarta(lin, col);
                bt.setPosicao(new Posicao(lin, col));
                bt.setOnAction(e -> viraCarta(e, userCountText, computerCountText));
                tab.add(bt, col, lin);
            }
        }

        btRestart.setOnAction(e -> {
            System.out.println("Restarting app");
            primaryStage.close();
            Platform.runLater(() -> {
                new Jogo().start(new Stage());
            });
        });

        // Monta a cena e exibe
        Scene scene = new Scene(tab);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void resetGame() {
    // Espera um tempo e depois reseta o jogo
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(1500),
                        e -> {
                            System.out.println("New Jogo App");
                            Platform.runLater(() -> {
                                new Jogo().start(new Stage());
                            });
                        })
        );
        timeline.play();
    }

    public void viraCarta(ActionEvent e, TextField userField, TextField computerField) {
        if (cartaCorrente != null) { // Se esta aguardando análise, retorna
            return;
        }
        // Identifica a carta acionada e reage de acordo
        Carta but = (Carta) e.getSource();
        if (but.getState() == CardState.USADA) {
            return;
        } else if (but.getState() == CardState.ABERTA) {
            return;
        } else {
            // Abre carta
            but.abre();
            but.defineImagem();
            // anota a carta corrente
            if (ultimaCartaAberta == null) {
                ultimaCartaAberta = but;
                // informa a primeira carta
                cJog.setCarta(but);
            } else {
                cartaCorrente = but;
                // Programa a analise da jogada em 2 segundo para permitir
                // que o jogador veja as cartas abertas
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.millis(1500),
                                ae -> analisaJogada(userField, computerField))
                );
                timeline.play();
            }
        }
    }

    public void analisaJogada(TextField userField, TextField computerField) {
        GameState state = cJog.setCarta(cartaCorrente);
        String StrPontosComp = "" + cJog.getPontosComputador();
        String StrPontosUser = "" + cJog.getPontosHumano();
        switch (state) {
            case ABRIU_CARTA1:
                ultimaCartaAberta = cartaCorrente;
                cartaCorrente.defineImagem();
                computerField.setText(StrPontosComp);
                userField.setText(StrPontosUser);
                return;
            case NAO_ACHOU_PAR:
                ultimaCartaAberta.defineImagem();
                cartaCorrente.defineImagem();
                computerField.setText(StrPontosComp);
                userField.setText(StrPontosUser);
                break;
            case ACHOU_PAR:
                ultimaCartaAberta.defineImagem();
                cartaCorrente.defineImagem();
                computerField.setText(StrPontosComp);
                userField.setText(StrPontosUser);
                break;
            case FIMDEPARTIDA:
                Alert msgBox = new Alert(AlertType.INFORMATION);
                msgBox.setHeaderText("Fim de Jogo");

                String str = "Humano: " + cJog.getPontosHumano() + " pontos\n";
                str += "Computador: " + cJog.getPontosComputador() + " pontos\n";

                str += "Pares Totais: " + cJog.getQuantidadePares() + "\n";

                if (cJog.getPontosHumano() > cJog.getPontosComputador()) {
                    str += "Vencedor: HUMANO !";
                } else {
                    str += "Vencedor: COMPUTADOR !";
                }
                msgBox.setContentText(str);
                msgBox.show();
                resetGame();
                return;
        }
        // Acoes que são iguais depois de se abrir a segunda carta
        ultimaCartaAberta = null;
        cartaCorrente = null;
        // Inicia jogada do computador
        jogadaComputadorParte1();
    }

    public void jogadaComputadorParte1() {
        primCartaComp = cJog.primeiraCartaComputador();
        segCartaComp = cJog.segundaCartaComputador();
        primCartaComp.defineImagem();
        segCartaComp.defineImagem();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(1000),
                        ae -> jogadaComputadorParte2())
        );
        timeline.play();
    }

    public void jogadaComputadorParte2() {
        if (cJog.completaJogadaComputador() == GameState.FIMDEPARTIDA) {
            String str = "Humano: " + cJog.getPontosHumano() + " pontos\n";
            str = str + "Computador: " + cJog.getPontosComputador() + " pontos";
            Alert msgBox = new Alert(AlertType.INFORMATION);
            msgBox.setHeaderText("Fim de Jogo");
            msgBox.setContentText(str);
            msgBox.show();
            return;
        }
        primCartaComp.defineImagem();
        segCartaComp.defineImagem();
    }
}
