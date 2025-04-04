# Developer Setup & Contribution Guide

This document outlines everything you need to get set up locally, run the app, and collaborate smoothly with the rest of the team — even if you’ve never used Vue, Node.js, or Git before.

If you're ever unsure, reach out in the team Discord.

---

## 1. Clone the Repo

Cloning a repo means you're downloading a copy of the project to your computer.

```bash
git@github.com:fsiidd/cypress.git
cd cypress
```

---

## 2. Working on a Feature Branch

### Working on an existing branch
If a branch already exists for a feature (like `login` or `signup`), use the following commands to switch to it and get the latest version:

```bash
git checkout branch-name
git pull origin branch-name
```

Example:
```bash
git checkout login
git pull origin navbar
```

> This moves you into the existing branch and downloads the latest updates for it.

### Creating a new branch (only if your feature doesn't have one yet)
If no one has started a branch for the feature you’re working on:

```bash
git checkout -b new-branch-name
```
This creates and switches you to a new branch.

Then push it to GitHub:
```bash
git push -u origin new-branch-name
```

### Making changes on a branch
Once you're on the correct branch:

```bash
# make changes in the code

git add .
git commit -m "Your short descriptive message"
git push origin branch-name
```

> Avoid generic commit messages like "fixed stuff." Be specific, like "added signin functionality".

---

## Development Tips

- Always pull the latest changes before you begin:
  ```bash
  git pull origin feature-branch-name
  ```

- Do not push to `main` unless the team agrees the feature is complete.

- Let teammates know what file(s) you're working on in Discord to avoid merge conflicts.

- Commit small chunks of work often so they are easier to review/revert and fix if needed.

---

## Done with Your Task?

1. Push your changes:
   ```bash
   git push origin feature-branch-name
   ```

2. Let the team know in Discord or GitHub what you pushed.

3. Ask for a review if your feature/PR is complete, or check with others before merging into `main`.

---
