# OS project
Each team (GUI and Code teams) will have a seperate branch to work on (Don't push to main branch) there are two
secondary branches already created one named code for the code team and the other named gui for the gui team.

## setup

First clone the repo:

`git clone https://github.com/MahmoudME-2005/OSproject`

Then enter the project's directory:

`cd OSproject`

Then jump into your branch:

* **For Code team:** `git checkout code`
* **For GUI team:**  `git checkout gui`

Then when you need to push use this (only for the first push):

* **For Code team:** `git push -u origin code`
* **For GUI team:**  `git push -u origin gui`

After this you can just use `git push` for any further pushing.

## How to update your local repo with your colleague's work:

To get your colleague's changes on the same team:

`git fetch origin`

NOTE: don't merge the code and gui branches together if you want to try the whole project create a third branch and merge both the gui and code branches their and keep this third branch local and don't push from it.
