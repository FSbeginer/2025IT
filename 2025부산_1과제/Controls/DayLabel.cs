using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025부산_1과제.Controls
{
    public partial class DayLabel : UserControl
    {
        public DateTime date { get; set; }
        public DayLabel()
        {
            InitializeComponent();
            if (date.DayOfWeek == DayOfWeek.Sunday)
            {
                label1.ForeColor = Color.Red;
                label2.ForeColor = Color.Red;
            }
            else if (date.DayOfWeek == DayOfWeek.Saturday)
            {
                label1.ForeColor= Color.Blue;
                label2.ForeColor= Color.Blue;
            }
        }
    }
}
