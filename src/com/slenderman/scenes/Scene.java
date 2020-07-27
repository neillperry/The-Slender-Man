package com.slenderman.scenes;

import com.slenderman.actors.Item;
import com.slenderman.actors.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

/**
 *  The abstract class Scene is the base class to all classes in the com
 *  .slenderman.scenes package.
 *
 */
public abstract class Scene {

  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_WHITE = "\u001B[37m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_GREEN = "\u001B[32m";

  protected Scene sceneToTheNorth; // turn into array possibly
  protected Scene sceneToTheSouth;
  protected Scene sceneToTheEast;
  protected Scene sceneToTheWest;
  protected HashMap<Directions, Scene> coordinateNames;

  protected String description;
  protected String sceneName;
  protected Scene currentScene;

  protected ArrayList<Item> itemsInScene;


  /*
   * =============================================
   * ============= Constructors ==================
   * =============================================
   */
  public Scene(){};

  // TODO: See if setters will work instread of directly accessing the fields
  public Scene(
    Scene sceneToTheNorth, Scene sceneToTheSouth,
    Scene sceneToTheEast, Scene sceneToTheWest) {
    this.sceneToTheNorth = sceneToTheNorth;
    this.sceneToTheSouth = sceneToTheSouth;
    this.sceneToTheEast = sceneToTheEast;
    this.sceneToTheWest = sceneToTheWest;
  }

  /*
   * =============================================
   * =========== Business Methods ================
   * =============================================
   */

  /**
   *  The entry point into all scene classes.
   *  The Game class will call `Scene.enter(in, player);` to start each
   *  Scene's story
   *
   * @param in
   * @param player
   * @throws InterruptedException
   */
  public abstract void enter() throws Exception;

  public void initializeCoordinate() {
    //for (Directions d : Directions.values())

    coordinateNames.put(Directions.east, sceneToTheEast);
    coordinateNames.put(Directions.west, sceneToTheWest);
    coordinateNames.put(Directions.north, sceneToTheNorth);
    coordinateNames.put(Directions.south, sceneToTheSouth);
  }

  /**
   *
   * @param in -> Scanner object for console input
   * @return String result of console input

  public String playerChoice(Scanner in) {
    return in.nextLine().toLowerCase().trim();
  }
*/

  /**
   *
   * @param direction
   * @return
   */
  public Scene changeScene(Directions direction) throws Exception {
    if (coordinateNames.containsKey(direction)) {
      return coordinateNames.get(direction);
    }
    else {
      System.out.println("Error: unknown direction " + direction);
      throw new Exception("You cannot go " + direction + " from here.");
    }
  }


  /**
   *
   * @param otherScene
   */
  public void connectEast(Scene otherScene){
    sceneToTheEast = otherScene;
    otherScene.sceneToTheWest = this;
  };


  /**
   *
   * @param otherScene
   */
  public void connectSouth(Scene otherScene){
    sceneToTheSouth = otherScene;
    otherScene.sceneToTheNorth = this;
  };


  /**
   *  Searches Player's inventory for an Item based on the Item's name
   * @param player -> Player object
   * @param itemName -> string representation of the Item's name
   * @return boolean
   */
  public Boolean playerHasItem(Player player, String itemName) {
    Collection<Item> playerInventory = player.getInventory();

    return playerInventory.stream()
             .anyMatch(item -> item.getItemName().equals(itemName));
  }


  /*
   * =============================================
   * =========== Accessor Methods ================
   * =============================================
   */

  // SET METHODS

  public void setSceneName(String sceneName) {
    this.sceneName = sceneName;
  }

  public void setItemsInScene(ArrayList<Item> itemsInScene) {
    this.itemsInScene = itemsInScene;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCurrentScene(Scene currentScene) {
    this.currentScene = currentScene;
  }

  public void setSceneToTheNorth(Scene sceneToTheNorth) {
    this.sceneToTheNorth = sceneToTheNorth;
  }

  public void setSceneToTheSouth(Scene sceneToTheSouth) {
    this.sceneToTheSouth = sceneToTheSouth;
  }

  public void setSceneToTheEast(Scene sceneToTheEast) {
    this.sceneToTheEast = sceneToTheEast;
  }

  public void setSceneToTheWest(Scene sceneToTheWest) {
    this.sceneToTheWest = sceneToTheWest;
  }


  // GET METHODS

  public String getSceneName() {
    return sceneName;
  }


  public ArrayList<Item> getItemsInScene() {
    return itemsInScene;
  }

  public String getDescription() {
    return description;
  }

  public Scene getSceneToTheNorth() {
    return sceneToTheNorth;
  }

  public Scene getSceneToTheWest() {
    return sceneToTheWest;
  }

  public Scene getSceneToTheEast() {
    return sceneToTheEast;
  }

  public Scene getSceneToTheSouth() {
    return sceneToTheSouth;
  }


  @Override public String toString() {
    return "Scene{" +
           "sceneToTheNorth=" + sceneToTheNorth +
           ", sceneToTheSouth=" + sceneToTheSouth +
           ", sceneToTheEast=" + sceneToTheEast +
           ", sceneToTheWest=" + sceneToTheWest +
           ", description='" + description + '\'' +
           ", sceneName='" + sceneName + '\'' +
           ", itemsInScene=" + itemsInScene +
           '}';
  }
}



