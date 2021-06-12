import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Drive extends Application {

	public File shareFile;
	
	public LinkedStack stackSource = new LinkedStack();
	public LinkedStack stackTemp = new LinkedStack();	//Stacks & Queues
	public Queue queueSource = new Queue();
	public Queue queueTemp = new Queue();
	
	public double totalCapital = 0;
	public double gainCapital = 0;			// Capital
	public double lossCapital = 0;
	
	public double X = 0, Y = 0, Z = 0,K=0;		// Daily Price
	
	
	public static void main(String[] args) {//Main Class

		Application.launch(args);

	}

	@Override
	public void start(Stage wind) throws Exception {
		
		BorderPane bp0 = new BorderPane();
		bp0.setId("pane");
		Scene scene0 = new Scene(bp0, 400, 450);
		scene0.getStylesheets().addAll(this.getClass().getResource("Style.css").toExternalForm());
		
		BorderPane bp1 = new BorderPane();
		bp1.setId("pane");
		Scene scene1 = new Scene(bp1, 400, 450);
		scene1.getStylesheets().addAll(this.getClass().getResource("Style.css").toExternalForm());
		
		BorderPane bp2 = new BorderPane();
		Scene scene2 = new Scene(bp2, 400, 450);
		BorderPane bp3 = new BorderPane();
		Scene scene3 = new Scene(bp3, 800, 500);
		BorderPane bp4 = new BorderPane();
		Scene scene4 = new Scene(bp4, 400, 450);
		BorderPane bp5 = new BorderPane();
		Scene scene5 = new Scene(bp5, 800, 500);
		
		readPrice();

		Label TopLabel0 = new Label("Welcome to the smart shares");
//		TopLabel0.setFont(new Font(22));
//		BorderPane.setAlignment(TopLabel0, Pos.CENTER);

		Label TopLabel1 = new Label("Please choose a process");
//		TopLabel1.setFont(new Font(22));
//		bp1.setTop(TopLabel1);
//		BorderPane.setAlignment(TopLabel1, Pos.CENTER);

		Label TopLabel2 = new Label("\n\n Company Share Price \nX = " + X + "  Y = " + Y + "  Z = " + Z);
		TopLabel2.setFont(new Font(22));
		bp2.setTop(TopLabel2);
		BorderPane.setAlignment(TopLabel2, Pos.CENTER);
		
		Label TopLabel3 = new Label("\nCompany Share Price\nX = " + X + "  Y = " + Y + "  Z = " + Z+" K = "+K);
		TopLabel3.setFont(new Font(22));
		bp3.setTop(TopLabel3);
		BorderPane.setAlignment(TopLabel3, Pos.CENTER);

		
		// File Chooser
		FileChooser fileChooser;
		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text File ", "*txt"),
				new ExtensionFilter("Exil Fil e ", "*.csv"));

		//Button
		Button menub = new Button("Back");
		Button browse = new Button("Browes");
		Button buyButton = new Button("Buy Share");
		Button sellButton = new Button("Sell Share");
		Button Capital = new Button("Capital");
		Button saveFile = new Button("Save Data");
		Button exit = new Button("Exit");
		Button showShares = new Button("My Shares");
		
//		browse.setFont(new Font(15));
//		showShares.setFont(new Font(17));
//		buyButton.setFont(new Font(17));
//		sellButton.setFont(new Font(17));
//		Capital.setFont(new Font(17));
//		exit.setFont(new Font(17));
//		browse.setFont(new Font(15));
//		saveFile.setFont(new Font(17));
		
		
		//First Secne
		VBox fs = new VBox();
		fs.setSpacing(15);
		Label gp0Lapel = new Label("Please choose your shares file");
		fs.getChildren().addAll(TopLabel0,gp0Lapel,browse);
		fs.setAlignment(Pos.CENTER);
		bp0.setCenter(fs);
		
		
		TextArea textArea = new TextArea();
		TextArea textAreaShow = new TextArea();
	
		//My Shares
		bp5.setCenter(textAreaShow);	
		
		// Menu
		VBox ss = new VBox();
		ss.setSpacing(15);
		ss.getChildren().addAll(TopLabel1,showShares,buyButton,sellButton,Capital,saveFile,exit);
		ss.setAlignment(Pos.CENTER);		
		bp1.setCenter(ss);

		// Buy Share
		Button buyShare = new Button("Buy");
		buyShare.setFont(new Font(15));
		Label sucBuy = new Label();
		GridPane gp2 = new GridPane();
		gp2.setVgap(15);
		gp2.setHgap(5);
		gp2.add(new Label("Number Of Share "), 0, 0); // label
		gp2.add(new Label("Please Chooce a company \nshare name "), 0, 1);
		TextField numberofShare = new TextField();
		gp2.add(numberofShare, 1, 0);
		gp2.add(buyShare, 1, 2);
		gp2.add(sucBuy, 0, 4);

		ComboBox<String> Combany = new ComboBox<String>();
		Combany.getItems().addAll("X", "Y", "Z");
		gp2.add(Combany, 1, 1);
		Combany.getSelectionModel().select(1);

		bp2.setCenter(gp2);
		gp2.setAlignment(Pos.CENTER);

		
		// Sell Share
		Button sellOld = new Button("Sell Old");
		Button sellNew = new Button("Sell New");
		sellOld.setFont(new Font(15));
		sellNew.setFont(new Font(15));
		
		GridPane gp3 = new GridPane();
		Label captial = new Label("Gain Capital = "+gainCapital+"\nLoss Capital = "+lossCapital+"\n"+"TotalCapital = "+totalCapital);
		captial.setFont(new Font(15));
		
		gp3.setVgap(15);
		gp3.setHgap(5);
		gp3.add(new Label("Number Of Share \n you want to sell"), 0, 0); // label
		gp3.add(new Label("Please Chooce a company \nshare name "), 0, 1);
		TextField numberofShareSell = new TextField();
		gp3.add(numberofShareSell, 1, 0);
		
		HBox ON = new HBox(sellOld,sellNew); 
		ON.setSpacing(10);
		gp3.add(ON, 1, 2);
		gp3.add(captial, 0, 3);
		
		ComboBox<String> CombanyS = new ComboBox<String>();
		CombanyS.getItems().addAll("X", "Y", "Z");
		gp3.add(CombanyS, 1, 1);
		CombanyS.getSelectionModel().select(1);

		bp3.setLeft(gp3);
		bp3.setRight(textArea);
		gp3.setAlignment(Pos.CENTER);

		
		//Captial
		GridPane gp4 = new GridPane();
		gp4.add(captial, 0, 0);
		bp4.setCenter(gp4);
		gp4.setAlignment(Pos.CENTER);
		
		
		
		
		// Button Actions
		menub.setOnAction(e -> {
			wind.setScene(scene1);
		});

		
		browse.setOnAction(e -> {
			shareFile = fileChooser.showOpenDialog(wind);

			if (shareFile.exists()) {
				Scanner in;
				try {
					in = new Scanner(shareFile);
					String[] t;
					while (in.hasNext()) {
						t = in.nextLine().trim().split(",");
						if (t.length == 4) {
							Share myShare = new Share(Integer.parseInt(t[0].trim()), Double.parseDouble(t[1].trim()),
									t[2].trim(), t[3]);
							stackSource.push(myShare);
							queueSource.enqueue(myShare);

						}
					}
					//stackSource.print();
				} catch (FileNotFoundException e1) {
					System.out.println(e1);
					}
				wind.setScene(scene1);
			}
			else
				wind.setScene(scene0);
			
		});

		buyButton.setOnAction(e -> {
			bp2.setBottom(menub);
			wind.setScene(scene2);
		});

		
		// Buy SHares
		buyShare.setOnAction(e -> {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date(System.currentTimeMillis());
			String datee = formatter.format(date);
			

			if (Combany.getSelectionModel().getSelectedItem().trim().equals("X")) {
				Share sX = new Share(Integer.parseInt(numberofShare.getText().trim()), X, "X", datee);
				sucBuy.setText("Successful purchase of "+ Integer.parseInt(numberofShare.getText().trim())+ " X shares");
				stackSource.push(sX);
				queueSource.enqueue(sX);
			} else if (Combany.getSelectionModel().getSelectedItem().trim().equals("Y")) {
				Share sY = new Share(Integer.parseInt(numberofShare.getText().trim()), Y, "Y", datee);
				sucBuy.setText("Successful purchase of "+ Integer.parseInt(numberofShare.getText().trim())+ " Y shares");
				stackSource.push(sY);
				queueSource.enqueue(sY);
			} else {
				Share sZ = new Share(Integer.parseInt(numberofShare.getText().trim()), Z, "Z", datee);
				sucBuy.setText("Successful purchase of "+ Integer.parseInt(numberofShare.getText().trim())+ " Z shares");
				stackSource.push(sZ);
				queueSource.enqueue(sZ);
				stackSource.print();
			}
		});

		sellButton.setOnAction(e -> {
			bp3.setBottom(menub);
			wind.setScene(scene3);

		});

		
		
		// Sell Old %%%%%%%%%
		
		sellOld.setOnAction(e ->{
			
			int count = 0;
			int sumShare = 0;
			int enteredNumber = Integer.parseInt(numberofShareSell.getText().trim());

			// Find Sum Of share in the selected Company from combo box
			while (queueSource.isEmpty() == false) {
				Node sh1 = queueSource.dequeue();
				int num1 = sh1.getData().getsNumber();
				String nameC = CombanyS.getSelectionModel().getSelectedItem().trim();
				if (sh1.getData().getsCompnay().equals(nameC)) {
					while (num1 != 0) {
						sumShare += num1;
						num1 = 0;
					}
				}
				queueTemp.enqueue(sh1.getData());
			}
			//set Back The Source Queue
			while (queueTemp.isEmpty() == false) {  
				Node t = queueTemp.dequeue();
				queueSource.enqueue(t.getData());
			}

			
			//Main Sell Functions 
			if (enteredNumber <= sumShare) {
				stackSource.clear();
				while (enteredNumber != count) {
					Node sh = queueSource.dequeue();
					int num = sh.getData().getsNumber();
					double oldPrice = 0;
					double newPrice = 0;
					double newOld=0;
					
					String nameC = CombanyS.getSelectionModel().getSelectedItem().trim();
					// System.out.println("\nNode sh Data :"+sh.getData());
					if (sh.getData().getsCompnay().equals(nameC)) {

						//The Capital Prices
						if (CombanyS.getSelectionModel().getSelectedItem().trim().equals("X")) {
							double price = sh.getData().getsPrice();
							newPrice = (enteredNumber * X);
							oldPrice = (price * enteredNumber);
							newOld = (newPrice - oldPrice);
							if (newOld >= 0) {
								gainCapital = newOld + gainCapital;
							}

							else {
								lossCapital -= newOld;
							}
							totalCapital=(gainCapital-lossCapital);

						} else if (CombanyS.getSelectionModel().getSelectedItem().trim().equals("Y")) {
							double price = sh.getData().getsPrice();
							newPrice = (enteredNumber * Y);
							oldPrice = (price * enteredNumber);
							newOld = (newPrice - oldPrice);
							if (newOld >= 0) {
								gainCapital = newOld + gainCapital;
							}

							else {
								lossCapital -= newOld;
							}
							totalCapital=(gainCapital-lossCapital);
						} else {
							double price = sh.getData().getsPrice();
							newPrice = (enteredNumber * Z);
							oldPrice = (price * enteredNumber);
							newOld = (newPrice - oldPrice);
							if (newOld >= 0) {
								gainCapital = newOld + gainCapital;
							}

							else {
								lossCapital -= newOld;
							}
							totalCapital=(gainCapital-lossCapital);
						}
						//Main Sell Function
						while (num != 0) {
							num--;
							count++;
							// queueSource.print();
							if (enteredNumber == count) {
								if (num == 0)
									break;
								else {
									Share tempShare = new Share(num, sh.getData().getsPrice(),
											sh.getData().getsCompnay(), sh.getData().getsDate());
									queueTemp.enqueue(tempShare);
									while (queueSource.isEmpty() == false) {
										Node sN = queueSource.dequeue();
										queueTemp.enqueue(sN.getData());
									}
									break;
								}
							}
						}
					} else {
						queueTemp.enqueue(sh.getData());

					}
				}
				while (queueTemp.isEmpty() == false) {
					Node sN = queueTemp.dequeue();
					queueSource.enqueue(sN.getData());
					stackSource.push(sN.getData());
				}
				System.out.println("\n\n");

				queueSource.print();
				
				captial.setText("Gain Capital = "+gainCapital+"\nLoss Capital = "+lossCapital+"\n"+"TotalCapital = "+totalCapital);
				String S = stackSource.print2();
				textArea.setText("\t\t Your Shares"+"\n"+S);
				System.out.println("gainCapital = " + gainCapital);
				System.out.println("lossCapital = " + lossCapital);
				System.out.println("totalCapital = " + totalCapital);

			}

			else {
				System.out.println("\nSorry You do not have enough shares to complete the sale");
			textArea.setText("\nSorry You do not have enough shares to complete the sale");}
		
		});
		
		
		// Sell New ##########
		
		sellNew.setOnAction(e -> {
			int count = 0;
			int sumShare = 0;
			int enteredNumber = Integer.parseInt(numberofShareSell.getText().trim());

			// Find Sum Of share in the selected Company from combo box
			while (stackSource.isEmpty() == false) {
				Node sh1 = stackSource.pop();
				int num1 = sh1.getData().getsNumber();
				String nameC = CombanyS.getSelectionModel().getSelectedItem().trim();
				
				if (sh1.getData().getsCompnay().equals(nameC)) {
					while (num1 != 0) {
						sumShare += num1;
						num1 = 0;
					}
				}	
				stackTemp.push(sh1.getData());
			}
			
			//set Back The sorce Stack
			while (stackTemp.isEmpty() == false) {
				Node t = stackTemp.pop();
				stackSource.push(t.getData());
			}


			//Main Sell Functions 
			if (enteredNumber <= sumShare) {
				queueSource.clear();
				while (enteredNumber != count) {
					Node sh = stackSource.pop();
					int num = sh.getData().getsNumber();
					double oldPrice = 0;
					double newPrice = 0;
					double newOld=0;
					
					String nameC = CombanyS.getSelectionModel().getSelectedItem().trim();
					// System.out.println("\nNode sh Data :"+sh.getData());
					if (sh.getData().getsCompnay().equals(nameC)) {

						//The Capital Prices
						if (CombanyS.getSelectionModel().getSelectedItem().trim().equals("X")) {
							double price = sh.getData().getsPrice();
							newPrice = (enteredNumber * X);
							oldPrice = (price * enteredNumber);
							newOld = (newPrice - oldPrice);
							if (newOld >= 0) {
								gainCapital = newOld + gainCapital;
							}

							else {
								lossCapital -= newOld;
							}
							totalCapital=(gainCapital-lossCapital);

						} else if (CombanyS.getSelectionModel().getSelectedItem().trim().equals("Y")) {
							double price = sh.getData().getsPrice();
							newPrice = (enteredNumber * Y);
							oldPrice = (price * enteredNumber);
							newOld = (newPrice - oldPrice);
							if (newOld >= 0) {
								gainCapital = newOld + gainCapital;
							}

							else {
								lossCapital -= newOld;
							}
							totalCapital=(gainCapital-lossCapital);
						} else {
							double price = sh.getData().getsPrice();
							newPrice = (enteredNumber * Z);
							oldPrice = (price * enteredNumber);
							newOld = (newPrice - oldPrice);
							if (newOld >= 0) {
								gainCapital = newOld + gainCapital;
							}

							else {
								lossCapital -= newOld;
							}
							totalCapital=(gainCapital-lossCapital);
						}
						
						//Sell shares 
						while (num != 0) {
							num--;
							count++;
//							 stackSource.print();
							if (enteredNumber == count) {
								if (num == 0)//if the Amount of the shares has empty go to find to another day
									break;
								else {//if the Amount of the shares has #Not empty# add it with new Node
									Share tempShare = new Share(num, sh.getData().getsPrice(),
											sh.getData().getsCompnay(), sh.getData().getsDate());
									stackTemp.push(tempShare);
									while (stackSource.isEmpty() == false) {
										Node sN = stackSource.pop();
										stackTemp.push(sN.getData());
									}
									break;
								}
							}
						}
					} else {
						stackTemp.push(sh.getData());
					}
				}
				while (stackTemp.isEmpty() == false) {
					Node sN = stackTemp.pop();
					stackSource.push(sN.getData());
					queueSource.enqueue(sN.getData());
				}
				System.out.println("\n\n");
				
				
				captial.setText("Gain Capital = "+gainCapital+"\nLoss Capital = "+lossCapital+"\nTotalCapital = "+totalCapital);
				String S = stackSource.print2();
				textArea.setText("\t\t Your Shares"+"\n"+S);
				
				System.out.println("gainCapital = " + gainCapital);
				System.out.println("lossCapital = " + lossCapital);
				System.out.println("totalCapital = " + totalCapital);

			}

			else {
				System.out.println("\nSorry You do not have enough shares to complete the sale");
				textArea.setText("\nSorry You are tooo poor right now ");
			}

		});
		
		Capital.setOnAction(e ->{
			bp4.setBottom(menub);
			wind.setScene(scene4);
			
		});
		
		saveFile.setOnAction(e -> {
            FileChooser fileChoosers = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChoosers.getExtensionFilters().add(extFilter);
 
            File file = fileChoosers.showSaveDialog(wind);
 
            if (file != null) {
                saveTextToFile(stackSource.print2(), file);
            }
        });
 

		exit.setOnAction(e ->{
			wind.close();
		});
		
		
		showShares.setOnAction(e ->{
			bp5.setBottom(menub);
			wind.setScene(scene5);
			
			String S = stackSource.print2();
			textAreaShow.setText("\t\t Your Shares"+"\n"+S);
		});


		wind.setScene(scene0);
		wind.setTitle("My Shares");
		wind.getIcons().add(new Image("icon.png")); 

		wind.show();

	}
	
	
	private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException e1) {
        	e1.printStackTrace();
        }
    }


	public void readPrice() {
		File f = new File("dailyPrice.txt");

		if (f.exists()) {
			Scanner in;
			try {
				in = new Scanner(f);
				String[] t;
				while (in.hasNext()) {
					t = in.nextLine().trim().split(",");
					if (t.length == 2) {

						if (t[0].trim().equals("X")) {
							X = Double.parseDouble(t[1].trim());

						} else if (t[0].trim().equals("Y")) {
							Y = Double.parseDouble(t[1].trim());
						} else if (t[0].trim().equals("K")) {
							K = Double.parseDouble(t[1].trim());}
						else
							Z = Double.parseDouble(t[1].trim());
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println(e);
			}

		} else {
			System.out.println("File Not Found");
		}
	}
}
