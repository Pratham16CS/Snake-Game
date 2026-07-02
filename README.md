# 🐍 Classic Snake Game in Java

[![Java Version](https://img.shields.io/badge/Java-SE%2017%2B-orange.svg)](https://www.oracle.com/java/)
[![GUI Framework](https://img.shields.io/badge/UI-Java%20Swing%20%2F%20AWT-blue.svg)](https://docs.oracle.com/javase/tutorial/uiswing/)

A lightweight, pixel-perfect 2D Snake Game engineered from scratch utilizing native **Java Swing** and **AWT** components. Featuring an integrated state-driven menu manager, dynamic collision-evaluation loops, an automated persistence model tracking high scores across runs, and focus-safe button handling for instant replays.

---

## 🎨 Features At A Glance

* **Interactive State Router (`Page1.java`)**: A dedicated splash context featuring explicit navigation flows into gameplay configurations or localized context help modals.
* **AWT Dialog Interception**: Leverages declarative informational overlays for real-time control walkthroughs without triggering window bloat.
* **Focus-Resilient UI Pipeline**: Custom button state management overrides focus stealing, allowing instant transitions back to active keyboard event reading loops.
* **Session-Persistent High Scores**: Real-time score compilation structures that accurately cache performance milestones across subsequent loops within a runtime session.
* **Synchronized Frame Packing**: Avoids standard Java rendering stutters by deferring window visibility hooks until structural dimensions match component dimensions.

---

## 🛠️ System Architecture

The game decouples window environments from behavioral contexts using a micro-modular layout configuration:

```text
📁 snake-game/
│
├── 📄 Snakegame.java  # Application Entry Context (Initializes Bootstrapper Loop)
├── 📄 Page1.java      # Splash & Navigation Canvas Environment 
├── 📄 Gameframe.java  # Native OS-Level Window Container Context (JFrame Container)
└── 📄 Gamepanel.java  # Collision Loop, Graphics Rendering Pipeline & Event Manager

```

### Execution Lifecyle Flow

1. **`Snakegame`** initializes and shifts viewport focus to the main splash screen (**`Page1`**).
2. Starting the game disposes of the splash asset pipeline and loads the primary target frame (**`Gameframe`**).
3. The frame encapsulates a high-performance grid component layer (**`Gamepanel`**) that manages key input listeners and game loop timers.

---

## 🚀 Getting Started & Compilation

### Prerequisites

* **Java Development Kit (JDK)**: Standard Edition 17 or greater recommended.

### Building via Terminal

Navigate to the root directory where your source `.java` files are located, then execute the following directives:

1. **Compile all compilation units simultaneously:**
```bash
javac Snakegame.java Page1.java Gameframe.java Gamepanel.java

```


2. **Launch the runtime execution thread:**
```bash
java Snakegame

```



---

## 🕹️ Controls & Mechanics Reference

| Action / Direction | Input Mapping | Rule / Event Boundary |
| --- | --- | --- |
| **Steer Up** | ⬆️ `Up Arrow` | Cannot steer directly backward into opposing vectors. |
| **Steer Down** | ⬇️ `Down Arrow` | Cannot steer directly backward into opposing vectors. |
| **Steer Left** | ⬅️ `Left Arrow` | Cannot steer directly backward into opposing vectors. |
| **Steer Right** | ➡️ `Right Arrow` | Cannot steer directly backward into opposing vectors. |
| **Consume Apple** | Automatic Intersection | Extends body index by `1` cell; increments baseline score by `1`. |
| **Hit Boundary / Self** | Automatic Collision | Drops running flags, halts the active background timer, and outputs Game Over metrics. |

---

## ⚙️ Core Engineering Fixes Included

This polished revision resolves several legacy issues common in custom Swing games:

* **Focus Leak Elimination (`setFocusable(false)`)**: Explicitly revokes UI focus targets from interaction fields like buttons, preventing input elements from trapping hardware key triggers away from the main canvas event registers.
* **Precise Grid Border Evaluation**: Adapts boundary validation checks from loose inequality constraints (`>`) to boundary tracking limits (`>=`), correcting structural flaws where segment entities could render outside the visible canvas coordinates.
* **State Matrix Sweeps**: Embeds structured initialization steps that zero out historical placement arrays during live hot-resets, preventing ghost segment artifacts.
* **Synchronized Layout Buffering**: Reorders thread layouts by enforcing `.pack()` constraints before invoking structural visibility (`.setVisible(true)`), protecting layouts against dimensional distortion and initialization flicker.

---
