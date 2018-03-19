Low Beams
=========

Low Beams is a cross-platform desktop Java application which provides a
convenient way of rendering a colored overlay on top of the desktop environment.
The primary target use case is dimming the display at night to make working in
darkness more comfortable for PC users. However, there may be other valid use
cases, as there are numerous configurable settings.

Motivation
----------

Low Beams was born out of complete dissatisfaction with the existing solutions
to the problem of uncomfortable screen brightness. These solutions and their
flaws are:

- Adjusting display hardware settings
	* Small buttons can be difficult to use, especially in darkness
	* Not all displays support saved profiles, requiring frequent adjustment
- Adjusting GPU display settings
	* High dependence on both platform and individual system
	* Reduced visual fidelity
	* Frequent failures due to bugs in GPU drivers and software
- OS nighttime color profile
	* High dependence on both platform and individual system
	* Poor configuration options
	* Potential for conflict with GPU settings
	* Warmer colors often do not address problem of blinding brightness
- Time-based color profile applications
	* Potential for conflict with GPU settings
	* Warmer colors often do not address problem of blinding brightness
	* Tendency to persist in GPU software after application removal

Approach
--------

Low Beams simply renders an entirely independent semi-transparent colored
overlay window on top of the desktop environment. This overlay also has a fully
transparent cursor window, which tracks the location of the mouse cursor,
allowing mouse events to bypass the overlay and be delivered by the OS to any
underlying application windows.

Efficiency
----------

Low Beams is a relatively inefficient way of dimming a PC display. Due to the
overlay's semi-transparent design and rapidly changing structure, it can result
in high CPU/GPU utilization. On the other hand, the other solutions listed above
generally have little to no performance overhead, and can achieve the desired
effect. Regardless, Low Beams' cross-platform approach is an important and
suitable alternative for when those other solutions fail.

Tradeoffs
---------

| PROS | CONS |
| ---- | ---- |
| Easy to use at night or in darkness | Poor efficiency |
| Cross-platform Java | Poor compatibility with some underlying applications|
| No system-level changes, permanent or otherwise | Poor compatibility with some virtual desktop configurations |
| Intuitive live configuration | Stealing window focus is considered bad practice, despite good intentions |
| Per-screen granularity | |

Donations
---------

While other approaches to monetization are planned, voluntary donations are also
greatly appreciated.

| PayPal |
| ------ |
| [![](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=R64XTP42DSNPJ) |