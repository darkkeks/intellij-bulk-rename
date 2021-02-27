# intellij-bulk-rename

![Build](https://github.com/DarkKeks/intellij-bulk-rename/workflows/Build/badge.svg)

<!-- Plugin description -->
Мне нужен был способ безопасно переименовать несколько сотен методов.
Попытки сделать это через `grep + sed` потерпели неудачу, так как пришлось бы руками фильтровать использования методов с совпадающими названиями.

Плагином добавляется action `Bulk Rename`, который принимает на вход текст в формате
```
fully.qualified.class.Name,fromMethodName,toMethodName
fully.qualified.class.Name,fromMethodName,toMethodName
fully.qualified.class.Name,fromMethodName,toMethodName
```

Для каждой строки дергается дефолтный rename без поиска в коментариях и "не java" файлах, чтобы не показывать диалог выбора что переименовывать, а что нет.
<!-- Plugin description end -->

## Installation

- Using IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "intellij-bulk-rename"</kbd> >
  <kbd>Install Plugin</kbd>
  
- Manually:

  Download the [latest release](https://github.com/DarkKeks/intellij-bulk-rename/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
