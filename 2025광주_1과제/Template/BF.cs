using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _2025광주_1과제.Model
{
    public partial class BF : Form
    {
        public BF()
        {
            InitializeComponent();
        }

        private void BF_Load(object sender, EventArgs e)
        {
            
        }

        protected override void OnShown(EventArgs e)
        {
            base.OnShown(e);
            ActiveControl = null;
        }

        public void showPage(Form next)
        {
            Hide(); 
            next.ShowDialog();
            next.Dispose();
            Show();
        }

        public void showPage(string title)
        {
            var stack = new Stack<BF>(Application.OpenForms.Cast<BF>());
            while (stack.Count>0)
            {
                var form = stack.Pop();
                if(form.Text == title)
                    break;
                else
                    form.Close();
            }
        }

        public virtual void UpdateForm()
        {

        }
    }
}
